package com.example.backend.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

    @GetMapping("/smth/{patientId}") //??
    public List<Visit> getPatientVisit(@PathVariable Long patientId) {
        return service.getPatientVisits(patientId);
    }

    @GetMapping("/smth/{doctorId}") //??
    public List<Visit> getDoctorVisit(@PathVariable Long doctorId) {
        return service.getDoctorVisits(doctorId);
    }

    @PutMapping("/.../{visitId}")
    public Visit updateVisitDate(@PathVariable Long visitId, @RequestBody Date newDate) {
        return service.updateVisitDate(visitId, newDate);
    }
}
