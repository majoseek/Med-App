package com.example.backend.user;

import com.example.backend.doctor.DoctorSignUpDto;
import com.example.backend.exceptions.EmailAlreadyUsed;
import com.example.backend.exceptions.InvalidCredentials;
import com.example.backend.exceptions.InvalidRole;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.patient.PatientSignUpDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/create/doctor")
    @ResponseBody
    ResponseEntity<?> createNewUserAsDoctor(@RequestBody @NotNull DoctorSignUpDto doctor) {
        try {
            User user = userService.createUserAsDoctor(doctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (EmailAlreadyUsed e) {
            return ResponseEntity.status(HttpStatus.IM_USED).body(Collections.singletonMap("error", e.getLocalizedMessage()));
        }
    }


    @PostMapping(path = "/create/patient")
    @ResponseBody
    ResponseEntity<?> createNewUserAsPatient(@RequestBody @NotNull PatientSignUpDto patient) {
        try {
            User user = userService.createUserAsPatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (EmailAlreadyUsed emailUsed) {
            return ResponseEntity.status(HttpStatus.IM_USED).body(Collections.singletonMap("error", emailUsed.getLocalizedMessage()));
        }
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    ResponseEntity<?> getUserData(@PathVariable Long id) {
        try {
            UserDataDto.UserData data = userService.getUserData(id);
            return ResponseEntity.ok(data);
        } catch (UserNotFound | InvalidRole invalidParam) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", invalidParam.getLocalizedMessage()));
        }
    }

    @PutMapping(path ="/edit/{id}")
    @ResponseBody
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

    @PostMapping(path = "/login")
    @ResponseBody
    ResponseEntity<?> loginUser(@RequestBody UserLogInDto userCredentials) {
        try {
            UserDataDto.UserData user = userService.login(userCredentials);
            return ResponseEntity.ok(user);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        } catch (InvalidCredentials invalidCredentials) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(invalidCredentials.getLocalizedMessage());
        }
    }
}
