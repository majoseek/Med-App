package com.example.backend.user;

import com.example.backend.doctor.DoctorSignUpDto;
import com.example.backend.exceptions.*;
import com.example.backend.patient.PatientSignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Optional;


@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/create/doctor", produces = "application/json")
    ResponseEntity<?> createNewUserAsDoctor(DoctorSignUpDto doctor) {
        try {
            User user = userService.createUserAsDoctor(doctor);
            Response keycloak = userService.registerUserCredentials(doctor.getEmail(), doctor.getPassword(), user.getId(), UserRole.DOCTOR);
            if (keycloak.getStatus() != 201) {
                userService.removeUser(user.getId());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to register user credentials(email may be incorrect)"));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new UserDataDto.DoctorData(user));
        } catch (EmailAlreadyUsed e) {
            return ResponseEntity.status(HttpStatus.IM_USED).body(Collections.singletonMap("error", e.getLocalizedMessage()));
        }
    }


    @PostMapping(path = "/create/patient", produces = "application/json")
    ResponseEntity<?> createNewUserAsPatient(PatientSignUpDto patient) {
        try {
            User user = userService.createUserAsPatient(patient);
            Response keycloak = userService.registerUserCredentials(patient.getEmail(), patient.getPassword(), user.getId(), UserRole.PATIENT);
            if (keycloak.getStatus() != 201) {
                userService.removeUser(user.getId());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to register user credentials(email may be used)"));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new UserDataDto.PatientData(user));
        } catch (EmailAlreadyUsed emailUsed) {
            return ResponseEntity.status(HttpStatus.IM_USED).body(Collections.singletonMap("error", emailUsed.getLocalizedMessage()));
        }
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<?> getUserData(@PathVariable Long id) {
        try {
            UserDataDto.UserData data = userService.getUserData(id);
            return ResponseEntity.ok(data);
        } catch (UserNotFound | InvalidRole invalidParam) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", invalidParam.getLocalizedMessage()));
        }
    }

    @PutMapping(path ="/edit/{id}", produces = "application/json")
    ResponseEntity<?> editUserData(@PathVariable Long id,
                                   @RequestParam(required = false) Optional<String> email,
                                   @RequestParam(required = false) Optional<String> password,
                                   @RequestParam(required = false) Optional<String> name,
                                   @RequestParam(required = false) Optional<String> surname) {
        try {
            final UserDataDto.UserData editedUser = userService.editUserData(id, email, password, name, surname);
            return ResponseEntity.ok(editedUser);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", userNotFound.getLocalizedMessage()));
        } catch (InvalidRole error) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Collections.singletonMap("error", error.getLocalizedMessage()));
        } catch (DataIntegrityViolationException error) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Collections.singletonMap("error", "Email is already used"));
        }
    }
}
