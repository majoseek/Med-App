package com.example.backend.visit;

import com.example.backend.doctor.Doctor;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.exceptions.VisitNotFound;
import com.example.backend.patient.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/visits/all")
    @ResponseBody
    public ResponseEntity<?> getAllVisits() {
        List<VisitDto> allVisits = service.getAllVisits().stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(allVisits);
    }

    @GetMapping("/visits/id/{visitId}")
    @ResponseBody
    public ResponseEntity<?> getVisitById(@PathVariable Long visitId) {
        try {
            VisitDto visit = convertToDto(service.getVisitById(visitId));
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(visitNotFound.getLocalizedMessage());
        }
    }

    //TODO make it work
    @GetMapping("/visits/allByDate/{dateOfVisits}")
    @ResponseBody
    public ResponseEntity<?> getVisitsByDate(@PathVariable String dateOfVisits) {
        Date date = java.sql.Date.valueOf(dateOfVisits);
        List<VisitDto> visits = service.getVisitsByDate(date).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/visits/allByLocation/{location}")
    @ResponseBody
    public ResponseEntity<?> getVisitsByLocation(@PathVariable String location) {
        List<VisitDto> visits = service.getVisitsByLocation(location).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(visits);
    }

    @Transactional
    @GetMapping("/visits/allByPatient/{patientId}")
    @ResponseBody
    public ResponseEntity<?> getPatientVisits(@PathVariable Long patientId) {
        try {
            List<VisitDto> patientsVisits = service.getPatientVisits(patientId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(patientsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @GetMapping("/visits/allByDoctor/{doctorId}")
    @ResponseBody
    public ResponseEntity<?> getDoctorVisit(@PathVariable Long doctorId) {
        try {
            List<VisitDto> patientsVisits = service.getDoctorVisits(doctorId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(patientsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    //TODO make it work
    @PutMapping("/visits/update/{visitId}")
    public ResponseEntity<?> editVisitData(@PathVariable Long visitId,
                                           @RequestBody(required = false) Optional<Date> date,
                                           @RequestBody(required = false) Optional<String> description,
                                           @RequestBody(required = false) Optional<String> location,
                                           @RequestBody(required = false) Optional<Doctor> doctor,
                                           @RequestBody(required = false) Optional<Patient> patient
                                           ) {
        try {
            VisitDto visit = convertToDto(service.editVisitData(visitId, date, description, location, doctor, patient));
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((visitNotFound.getLocalizedMessage()));
        }
    }

    @PostMapping("/visits/create")
    public ResponseEntity<?> createVisit(@RequestBody CreateVisitDto createVisitDto) {
        //Visit visit = service.createVisit(date, description, location, doctor, patient);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/visits/id/{visitId}")
    public ResponseEntity<?> deleteVisit(@PathVariable Long visitId) {
        service.delete(visitId);
        return ResponseEntity.ok(visitId);
    }
}
