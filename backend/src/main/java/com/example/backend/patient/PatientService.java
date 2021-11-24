package com.example.backend.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private PatientRepository repository;

    @Autowired
    PatientService(PatientRepository repository) {this.repository = repository;}

    public List<Patient> getPatients() {
        return ResponseEntity.ok(repository.findAll()).getBody();
    }
}
