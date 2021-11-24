package com.example.backend.user;

import com.example.backend.doctor.Doctor;
import com.example.backend.doctor.DoctorRepository;
import com.example.backend.exceptions.EmailAlreadyUsed;
import com.example.backend.exceptions.InvalidCredentials;
import com.example.backend.exceptions.InvalidRole;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUserAsDoctor(String email, String password, String role, String name, String surname, String specialization) throws EmailAlreadyUsed {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyUsed("User with this mail already exists!");
        }
        final User newUser = new User();
        final Doctor newDoctor = new Doctor();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(role);
        newDoctor.setSpecialization(specialization);
        newDoctor.setName(name);
        newDoctor.setSurname(surname);
        newDoctor.setUserByUserId(newUser);
        newUser.setDoctorById(newDoctor);
        userRepository.save(newUser);
        return newUser;
    }

    public User createUserAsPatient(String email, String password, String role, String name, String surname, String pesel) throws EmailAlreadyUsed {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyUsed("User with this mail already exists!");
        }
        final User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(role);
        final Patient patient = new Patient();
        patient.setName(name);
        patient.setSurname(surname);
        patient.setPesel(pesel);
        newUser.setPatientById(patient);
        patient.setUserByUserId(newUser);
        userRepository.save(newUser);
        return newUser;
    }

    public UsersData.UserData getUserData(Long id) throws UserNotFound, InvalidRole {
        final User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound(String.format("User with id=%d does not exists", id)));
        switch (user.getRole()) {
            case "PATIENT":
                return new UsersData.PatientData(user);
            case "DOCTOR":
                return new UsersData.DoctorData(user);
            case "ADMIN":
                return new UsersData.AdminData(user);
            default:
                throw new InvalidRole("Unknown role of the user");
        }
    }

    public UsersData.UserData editUserData(Long id, Optional<String> email, Optional<String> password, Optional<String> name, Optional<String> surname) throws UserNotFound, InvalidRole, DataIntegrityViolationException {
        final User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound(String.format("User with id=%d does not exists", id)));
        password.ifPresent(user::setPassword);
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

    // Temporary login withour spring security, hashing passwords etc.
    public User login(String email, String encodedPassword) throws UserNotFound, InvalidCredentials {
        final User userByEmail = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFound("User with this email does not exist"));
        if (userByEmail.getPassword().equals(encodedPassword)) {
            return userByEmail;
        }
        throw new InvalidCredentials("Incorrect email or password");
    }
}
