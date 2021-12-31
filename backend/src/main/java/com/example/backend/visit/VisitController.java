package com.example.backend.visit;

import com.example.backend.exceptions.UserNotFound;
import com.example.backend.exceptions.VisitNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/visits/allByDate/{dateOfVisits}")
    @ResponseBody
    public ResponseEntity<?> getVisitsByDate(@PathVariable String dateOfVisits) {
        List<VisitDto> visits = service.getVisitsByDate(LocalDate.parse(dateOfVisits, DateTimeFormatter.ISO_DATE)).stream().map(this::convertToDto).collect(Collectors.toList());
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
            List<VisitDto> doctorsVisits = service.getDoctorVisits(doctorId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(doctorsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @GetMapping("/doctor/{doctorId}/countVisits")
    @ResponseBody
    public ResponseEntity<?> getVisitCountByMonth(@PathVariable Long doctorId,
                                                  @RequestParam Map<String, Integer> month) {
        Integer monthInt = month.get("month");
        Integer visitCount = service.getVisitCountByMonth(doctorId, monthInt);
        return ResponseEntity.ok(visitCount);
    }

    @PutMapping("/visits/updateDate/{visitId}")
    public ResponseEntity<?> updateVisitDate(@PathVariable Long visitId, @RequestBody Map<String, String> newDate) {
        try {
            VisitDto visit = convertToDto(service.updateVisitDate(visitId, LocalDateTime.parse(newDate.get("newDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((visitNotFound.getLocalizedMessage()));
        }
    }

    @PutMapping("/visits/updateLocation/{visitId}")
    public ResponseEntity<?> updateVisitLocation(@PathVariable Long visitId, @RequestBody Map<String, String> newLocation) {
        try {
            VisitDto visit = convertToDto(service.updateVisitLocation(visitId, newLocation.get("newLocation")));
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((visitNotFound.getLocalizedMessage()));
        }
    }

    @PutMapping("/visits/updateDescription/{visitId}")
    public ResponseEntity<?> updateVisitDescription(@PathVariable Long visitId, @RequestBody Map<String, String> newDescription) {
        try {
            VisitDto visit = convertToDto(service.updateVisitDescription(visitId, newDescription.get("newDescription")));
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((visitNotFound.getLocalizedMessage()));
        }
    }

    @PostMapping("/visits/create")
    public ResponseEntity<?> createVisit(@RequestBody CreateVisitDto createVisitDto) {
        try {
            VisitDto visit = convertToDto(service.createVisit(createVisitDto.getDate(),
                    createVisitDto.getDescription(), createVisitDto.getLocation(),
                    createVisitDto.getDoctor_id(),
                    createVisitDto.getPatient_id()));
            return ResponseEntity.ok(visit);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((userNotFound.getLocalizedMessage()));
        }

    }

    @DeleteMapping("/visits/id/{visitId}")
    public ResponseEntity<?> deleteVisit(@PathVariable Long visitId) {
        service.delete(visitId);
        return ResponseEntity.ok(visitId);
    }
}
