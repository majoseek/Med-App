package com.example.backend.patient;

import com.example.backend.visit.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PatientController {

    private PatientRepository repository;

    @Autowired
    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/smth") //???
    public ResponseEntity<Iterable<Patient>> getPatients() {
        return ResponseEntity.ok(repository.findAll());
    }
}
