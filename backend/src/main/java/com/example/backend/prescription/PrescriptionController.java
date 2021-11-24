package com.example.backend.prescription;


import com.example.backend.patient.Patient;
import com.example.backend.visit.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/...")
    public Prescription createPrescription(Prescription newPrescription) {
        return service.save(newPrescription);
    }

    @DeleteMapping("/.../{prescriptionId}")
    public void deletePrescription(
            @PathVariable Long prescriptionId) throws ResourceNotFoundException {
        var isDeleted= service.delete(prescriptionId);
        if(!isDeleted)
            throw new ResourceNotFoundException("Could not find prescription with id: " + prescriptionId);
    }
}
