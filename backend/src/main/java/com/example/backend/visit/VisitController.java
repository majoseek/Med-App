package com.example.backend.visit;

import com.example.backend.doctor.Doctor;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.exceptions.VisitNotFound;
import com.example.backend.patient.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class VisitController {

    private final VisitService service;
    private final ModelMapper modelMapper;

    @Autowired
    public VisitController(VisitService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    public VisitDto convertToDto(Visit visit) { return modelMapper.map(visit, VisitDto.class); }

    @GetMapping("/visits")
    @ResponseBody
    public ResponseEntity<?> getAllVisits() {
        List<VisitDto> allVisits = service.getAllVisits().stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(allVisits);
    }

    @GetMapping("/visits/{visitId}")
    @ResponseBody
    public ResponseEntity<?> getVisitById(@PathVariable Long visitId) {
        try {
            VisitDto visit = convertToDto(service.getVisitById(visitId));
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(visitNotFound.getLocalizedMessage());
        }
    }

    // czy Date czy tylko dzien
    @GetMapping("/visits/{dateOfVisits}") // czy to potrzebuje wyjatkow
    @ResponseBody
    public ResponseEntity<?> getVisitsByDate(@PathVariable Date dateOfVisits) {
        List<VisitDto> visits = service.getVisitsByDate(dateOfVisits).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/visits/{location}") // czy to potrzebuje wyjatkow
    @ResponseBody
    public ResponseEntity<?> getVisitsByLocation(@PathVariable String location) {
        List<VisitDto> visits = service.getVisitsByLocation(location).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/patient/{patientId}/visits")
    @ResponseBody
    public ResponseEntity<?> getPatientVisits(@PathVariable Long patientId) {
        try {
            List<VisitDto> patientsVisits = service.getPatientVisits(patientId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(patientsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @GetMapping("/doctor/{doctorId}/visits")
    @ResponseBody
    public ResponseEntity<?> getDoctorVisit(@PathVariable Long doctorId) {
        try {
            List<VisitDto> patientsVisits = service.getDoctorVisits(doctorId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(patientsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @PutMapping("/visits/{visitId}")
    public ResponseEntity<?> updateVisitDate(@PathVariable Long visitId, @RequestBody Date newDate) {
        try {
            VisitDto visit = convertToDto(service.updateVisitDate(visitId, newDate));
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
