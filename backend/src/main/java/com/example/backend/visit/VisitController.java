package com.example.backend.visit;

import com.example.backend.doctor.Doctor;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.exceptions.VisitNotFound;
import com.example.backend.patient.Patient;
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

    @GetMapping("/visits")
    @ResponseBody
    public ResponseEntity<?> getAllVisits() {
        List<Visit> allVisits = service.getAllVisits();
        return ResponseEntity.ok(allVisits);
    }

    @GetMapping("/visits/{visitId}")
    @ResponseBody
    public ResponseEntity<?> getVisitById(@PathVariable Long visitId) {
        try {
            Visit visit = service.getVisitById(visitId);
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(visitNotFound.getLocalizedMessage());
        }
    }

    // czy Date czy tylko dzien
    @GetMapping("/visits/{dateOfVisits}") // czy to potrzebuje wyjatkow
    @ResponseBody
    public ResponseEntity<?> getVisitsByDate(@PathVariable Date dateOfVisits) {
        List<Visit> visits = service.getVisitsByDate(dateOfVisits);
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/visits/{location}") // czy to potrzebuje wyjatkow
    @ResponseBody
    public ResponseEntity<?> getVisitsByLocation(@PathVariable String location) {
        List<Visit> visits = service.getVisitsByLocation(location);
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/{patientId}/visits")
    @ResponseBody
    public ResponseEntity<?> getPatientVisits(@PathVariable Long patientId) {
        try {
            List<Visit> patientsVisits = service.getPatientVisits(patientId);
            return ResponseEntity.ok(patientsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @GetMapping("/{doctorId}/visits")
    @ResponseBody
    public ResponseEntity<?> getDoctorVisit(@PathVariable Long doctorId) {
        try {
            List<Visit> patientsVisits = service.getDoctorVisits(doctorId);
            return ResponseEntity.ok(patientsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @PutMapping("/visits/{visitId}")
    public ResponseEntity<?> updateVisitDate(@PathVariable Long visitId, @RequestBody Date newDate) {
        try {
            Visit visit = service.updateVisitDate(visitId, newDate);
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((visitNotFound.getLocalizedMessage()));
        }
    }

    @PostMapping("/visits")
    public ResponseEntity<?> createVisit(@RequestBody Date date,
                                         @RequestBody String description,
                                         @RequestBody String location,
                                         @RequestBody Doctor doctor,
                                         @RequestBody Patient patient) {
        Visit visit = service.createVisit(date, description, location, doctor, patient);
        return ResponseEntity.ok(visit);
    }

    @DeleteMapping("/visits/{visitId}")
    public ResponseEntity<?> deleteVisit(@PathVariable Long visitId){
        service.delete(visitId);
        return ResponseEntity.ok(visitId);
    }
}
