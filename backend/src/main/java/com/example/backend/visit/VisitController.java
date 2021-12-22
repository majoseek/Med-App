package com.example.backend.visit;

import com.example.backend.doctor.Doctor;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.exceptions.VisitNotFound;
import com.example.backend.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class VisitController {

    private final VisitService service;

    @Autowired
    public VisitController(VisitService service) {
        this.service = service;
    }

    @GetMapping("/visits/all")
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

    @GetMapping("/patient/{patientId}/visits")
    @ResponseBody
    public ResponseEntity<?> getPatientVisits(@PathVariable Long patientId) {
        try {
            List<Visit> patientsVisits = service.getPatientVisits(patientId);
            return ResponseEntity.ok(patientsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @GetMapping("/doctor/{doctorId}/visits")
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
    public ResponseEntity<?> editVisitData(@PathVariable Long visitId,
                                           @RequestBody(required = false) Optional<Date> date,
                                           @RequestBody(required = false) Optional<String> description,
                                           @RequestBody(required = false) Optional<String> location,
                                           @RequestBody(required = false) Optional<Doctor> doctor,
                                           @RequestBody(required = false) Optional<Patient> patient
                                           ) {
        try {
            Visit visit = service.editVisitData(visitId, date, description, location, doctor, patient);
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((visitNotFound.getLocalizedMessage()));
        }
    }

    @PostMapping("/visits")
    public ResponseEntity<?> createVisit(@RequestBody CreateVisitDto createVisitDto) {
        //Visit visit = service.createVisit(date, description, location, doctor, patient);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/visits/{visitId}")
    public ResponseEntity<?> deleteVisit(@PathVariable Long visitId) {
        service.delete(visitId);
        return ResponseEntity.ok(visitId);
    }
}
