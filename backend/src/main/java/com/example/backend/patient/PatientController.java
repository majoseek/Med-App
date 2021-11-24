package com.example.backend.patient;

import com.example.backend.visit.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    private PatientService service;

    @Autowired
    public PatientController(PatientService service) {this.service = service;}

    @GetMapping("/smth") //???
    public List<Patient> getPatients() {
        return service.getPatients();
    }
}
