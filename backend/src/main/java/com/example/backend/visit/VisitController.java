package com.example.backend.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@SpringBootApplication
public class VisitController {

    private VisitRepository repository;

    @Autowired
    public VisitController(VisitRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/smth{patientId}") //??
    public ResponseEntity<Iterable<Visit>> getPatientVisit(@PathVariable Long patientId) {
        return ResponseEntity.ok(repository.findAllByPatientByPatientUserId(patientId));
    }

    @GetMapping("/smth{doctorId}") //??
    public ResponseEntity<Iterable<Visit>> getDoctorVisit(@PathVariable Long doctorId) {
        return ResponseEntity.ok(repository.findAllByDoctorByDoctorUserId(doctorId));
    }

    @PutMapping("/...{visitId}")
    public ResponseEntity<Visit> updateVisitDate(@PathVariable Long visitId, @RequestBody Date newDate)
            throws ResourceNotFoundException {
        Visit visit = repository.findById(visitId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit not found on :: "+ visitId));
        visit.setDate(newDate);
        final Visit updatedVisit = repository.save(visit);
        return ResponseEntity.ok(updatedVisit);
    }
}
