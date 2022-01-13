package com.example.backend.user;

import com.example.backend.doctor.Doctor;
import com.example.backend.doctor.DoctorSignUpDto;
import com.example.backend.exceptions.*;
import com.example.backend.patient.Patient;
import com.example.backend.patient.PatientSignUpDto;
import org.apache.http.util.EntityUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passEncoder;

    @Value("${keycloak.auth-server-url}")
    private String authPath;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passEncoder) {
        this.userRepository = userRepository;
        this.passEncoder = passEncoder;
    }

    public User createUserAsDoctor(DoctorSignUpDto doctor) throws EmailAlreadyUsed {
        if (userRepository.existsByEmail(doctor.getEmail())) {
            throw new EmailAlreadyUsed("User with this mail already exists!");
        }
        final User newUser = new User();
        final Doctor newDoctor = new Doctor();
        newUser.setEmail(doctor.getEmail());
        newUser.setPassword(passEncoder.encode(doctor.getPassword()));
        newUser.setRole("DOCTOR");
        newDoctor.setSpecialization(doctor.getSpecialization());
        newDoctor.setName(doctor.getName());
        newDoctor.setSurname(doctor.getSurname());
        newDoctor.setUserByDoctorId(newUser);
        newUser.setDoctorById(newDoctor);
        userRepository.save(newUser);
        return newUser;
    }

    public Response registerUserCredentials(String email, String password, Long id, UserRole role) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(authPath)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .realm("clinic-spring")
                .clientId("clinic-springSecurityAdmin")
                .clientSecret("ojHvx4QG2XWK6Vq7vtxyLKXCGllxulJ3")
                .resteasyClient(
                        new ResteasyClientBuilder()
                                .connectionPoolSize(10).build()
                ).build();
        RealmResource realmResource = keycloak.realm("clinic-spring");
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEmail(email);
        CredentialRepresentation passwordCred = new CredentialRepresentation();

        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);
        passwordCred.setTemporary(false);

        userRepresentation.setAttributes(Collections.singletonMap("db_id", List.of(id.toString())));

        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(List.of(passwordCred));
        RoleRepresentation roles = realmResource.roles().get(role.roleStr).toRepresentation();
        keycloak.tokenManager().getAccessToken();
        Response response = realmResource.users().create(userRepresentation);
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
        realmResource.users().get(userId).roles().realmLevel().add(List.of(roles));
        return response;
    }

    public User getByUsername(String email) throws UserNotFound {
        return  userRepository.findByEmail(email).orElseThrow(() -> new UserNotFound("User with this email does not exist"));
    }

    public User createUserAsPatient(PatientSignUpDto patient) throws EmailAlreadyUsed {
        if (userRepository.existsByEmail(patient.getEmail())) {
            throw new EmailAlreadyUsed("User with this mail already exists!");
        }
        final User newUser = new User();
        newUser.setEmail(patient.getEmail());
        newUser.setPassword(passEncoder.encode(patient.getPassword()));
        newUser.setRole("PATIENT");
        final Patient newPatient = new Patient();
        newPatient.setName(patient.getName());
        newPatient.setSurname(patient.getSurname());
        newPatient.setPesel(patient.getpesel());
        newUser.setPatientById(newPatient);
        newPatient.setUserByPatientId(newUser);
        userRepository.save(newUser);
        return newUser;
    }

    public UserDataDto.UserData getUserData(Long id) throws UserNotFound, InvalidRole {
        final User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound(String.format("User with id=%d does not exist", id)));
        switch (user.getRole()) {
            case "PATIENT":
                return new UserDataDto.PatientData(user);
            case "DOCTOR":
                return new UserDataDto.DoctorData(user);
            case "ADMIN":
                return new UserDataDto.AdminData(user);
            default:
                throw new InvalidRole("Unknown role of the user");
        }
    }

    public UserDataDto.UserData editUserData(Long id, Optional<String> email, Optional<String> password, Optional<String> name, Optional<String> surname) throws UserNotFound, InvalidRole, DataIntegrityViolationException {
        final User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound(String.format("User with id=%d does not exist", id)));
        if (password.isPresent()) {
            password.ifPresent(user::setPassword);

        }
        email.ifPresent(user::setEmail);
        if (Objects.equals(user.getRole(), "DOCTOR")) {
            name.ifPresent(user.getDoctorById()::setName);
            surname.ifPresent(user.getDoctorById()::setSurname);
        }
        if (Objects.equals(user.getRole(), "PATIENT")) {
            name.ifPresent(user.getPatientById()::setName);
            surname.ifPresent(user.getPatientById()::setSurname);
        }
        userRepository.save(user);
        return getUserData(id);
    }

    // Temporary login without spring security, hashing passwords etc.
    public UserDataDto.UserData login(UserLogInDto credentials) throws UserNotFound, InvalidCredentials {
        final User userByEmail = userRepository.findByEmail(credentials.getEmail()).orElseThrow(() -> new UserNotFound("User with this email does not exist"));
        if (userByEmail.getPassword().equals(credentials.getPassword())) {
            return new UserDataDto.UserData(userByEmail);
        }
        throw new InvalidCredentials("Incorrect email or password");
    }

    public Integer removeUser(Long id) {
        return userRepository.deleteUserById(id);
    }

    public User findMatchingUser(String email, String password) throws UserNotFound, InvalidCredentials {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFound("User not found"));
        if (passEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new InvalidCredentials("Invalid password");
    }

    public ResponseEntity<Map> logInUser(String username, String password) throws IOException, InterruptedException, InvalidCredentials {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();

        map.add("grant_type", "password");
        map.add("client_id", "clinic-springSecurity");
        map.add("username", username);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        RestTemplate restTemplate=new RestTemplate();
        try {
            return restTemplate.postForEntity(authPath + "/realms/clinic-spring/protocol/openid-connect/token", request, Map.class);
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new InvalidCredentials("Invalid credentials");
        }
    }
}
