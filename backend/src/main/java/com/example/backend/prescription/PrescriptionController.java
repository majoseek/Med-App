package com.example.backend.prescription;


import com.example.backend.patient.Patient;
import com.example.backend.visit.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrescriptionController {

    private PrescriptionService service;

    @Autowired
    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @GetMapping("/smth/{patientId}") //???
    public List<Prescription> getPatientPrescription(@PathVariable Long patientId) {
        return service.getPatientPrescription(patientId);
    }
}
