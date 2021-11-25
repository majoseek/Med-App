package com.example.backend.visit;

import com.example.backend.exceptions.UserNotFound;
import com.example.backend.exceptions.VisitNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class VisitController {

    private VisitService service;

    @Autowired
    public VisitController(VisitService service) {
        this.service = service;
    }

    @GetMapping("/{visitId}")
    public ResponseEntity<?> getVisitById(@PathVariable Long visitId) {
        try {
            Visit visit = service.getVisitById(visitId);
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(visitNotFound.getLocalizedMessage());
        }
    }

    @GetMapping("/{dateOfVisits}") // czy to potrzebuje wyjatkow
    public ResponseEntity<?> getVisitsByDate(@PathVariable Date dateOfVisits) {
        List<Visit> visits = service.getVisitsByDate(dateOfVisits);
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/{location}") // czy to potrzebuje wyjatkow
    public ResponseEntity<?> getVisitsByLocation(@PathVariable String location) {
        List<Visit> visits = service.getVisitsByLocation(location);
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getPatientVisits(@PathVariable Long patientId) {
        try {
            List<Visit> patientsVisits = service.getPatientVisits(patientId);
            return ResponseEntity.ok(patientsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((userNotFound.getLocalizedMessage()));
        }
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<?> getDoctorVisit(@PathVariable Long doctorId) {
        try {
            List<Visit> patientsVisits = service.getDoctorVisits(doctorId);
            return ResponseEntity.ok(patientsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((userNotFound.getLocalizedMessage()));
        }
    }

    @PutMapping("/{visitId}") // czy nie powinno byc void
    public Visit updateVisitDate(@PathVariable Long visitId, @RequestBody Date newDate) {
        return service.updateVisitDate(visitId, newDate);
    }

    @PostMapping("/...") // czy nie powinno byc void
    public Visit createVisit(@RequestBody Visit newVisit) {
        return service.save(newVisit);
    }

    @DeleteMapping("/{visitId}")
    public void deleteVisit(
            @PathVariable Long visitId) throws VisitNotFound {
        var isDeleted= service.delete(visitId);
        if(!isDeleted)
            throw new VisitNotFound("Could not find prescription with id: " + visitId);
    }
}
