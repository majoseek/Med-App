package com.example.backend.user;

import com.example.backend.doctor.Doctor;
import com.example.backend.doctor.DoctorSignUpDto;
import com.example.backend.exceptions.EmailAlreadyUsed;
import com.example.backend.exceptions.InvalidCredentials;
import com.example.backend.exceptions.InvalidRole;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.patient.Patient;
import com.example.backend.patient.PatientSignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUserAsDoctor(DoctorSignUpDto doctor) throws EmailAlreadyUsed {
        if (userRepository.existsByEmail(doctor.getEmail())) {
            throw new EmailAlreadyUsed("User with this mail already exists!");
        }
        final User newUser = new User();
        final Doctor newDoctor = new Doctor();
        newUser.setEmail(doctor.getEmail());
        newUser.setPassword(doctor.getEmail());
        newUser.setRole("DOCTOR");
        newDoctor.setSpecialization(doctor.getSpecialization());
        newDoctor.setName(doctor.getName());
        newDoctor.setSurname(doctor.getSurname());
        newDoctor.setUserByDoctorId(newUser);
        newUser.setDoctorById(newDoctor);
        userRepository.save(newUser);
        return newUser;
    }

    public User createUserAsPatient(PatientSignUpDto patient) throws EmailAlreadyUsed {
        if (userRepository.existsByEmail(patient.getEmail())) {
            throw new EmailAlreadyUsed("User with this mail already exists!");
        }
        final User newUser = new User();
        newUser.setEmail(patient.getEmail());
        newUser.setPassword(patient.getPassword());
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
        final User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound(String.format("User with id=%d does not exists", id)));
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

    // Temporary login without spring security, hashing passwords etc.
    public UserDataDto.UserData login(UserLogInDto credentials) throws UserNotFound, InvalidCredentials {
        final User userByEmail = userRepository.findByEmail(credentials.getEmail()).orElseThrow(() -> new UserNotFound("User with this email does not exist"));
        if (userByEmail.getPassword().equals(credentials.getPassword())) {
            return new UserDataDto.UserData(userByEmail);
        }
        throw new InvalidCredentials("Incorrect email or password");
    }
}
