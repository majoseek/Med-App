package com.example.backend.visit;

import com.example.backend.Utilities;
import com.example.backend.exceptions.InvalidPrincipal;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.exceptions.VisitNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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

    public VisitDto convertToDto(Visit visit) {
        return modelMapper.map(visit, VisitDto.class);
    }

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"})
    @GetMapping(path = "/visits", produces = "application/json")
    public ResponseEntity<?> getAllVisits() {
        List<VisitDto> allVisits = service.getAllVisits().stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(allVisits);
    }

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"})
    @GetMapping(path = "/visits/id/{visitId}", produces = "application/json")
    public ResponseEntity<?> getVisitById(@PathVariable Long visitId) {
        try {
            VisitDto visit = convertToDto(service.getVisitById(visitId));
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(visitNotFound.getLocalizedMessage());
        }
    }

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"})
    @GetMapping(path = "/visits/allByDate/{dateOfVisits}", produces = "application/json")
    public ResponseEntity<?> getVisitsByDate(@PathVariable String dateOfVisits) {
        List<VisitDto> visits = service.getVisitsByDate(LocalDate.parse(dateOfVisits, DateTimeFormatter.ISO_DATE)).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(visits);
    }

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"})
    @GetMapping(path = "/visits/{location}", produces = "application/json") // czy to potrzebuje wyjatkow
    public ResponseEntity<?> getVisitsByLocation(@PathVariable String location) {
        List<VisitDto> visits = service.getVisitsByLocation(location).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(visits);
    }

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"})
    @GetMapping(path = "/patient/{patientId}/visits", produces = "application/json")
    public ResponseEntity<?> getPatientVisits(@PathVariable Long patientId) {
        try {
            List<VisitDto> patientsVisits = service.getPatientVisits(patientId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(patientsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @RolesAllowed("ROLE_doctor")
    @GetMapping(path = "/doctor/{doctorId}/visits", produces = "application/json")
    public ResponseEntity<?> getDoctorVisit(@PathVariable Long doctorId) {
        try {
            List<VisitDto> doctorsVisits = service.getDoctorVisits(doctorId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(doctorsVisits);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @RolesAllowed("ROLE_doctor")
    @GetMapping(path = "/doctor/countVisits", produces = "application/json")
    public ResponseEntity<?> getVisitCount(Principal principal) throws InvalidPrincipal {
        Long doctorId = Utilities.getUserDbIdFromPrincipal(principal);
        Integer visitCount = service.getVisitCountByMonth(doctorId);
        return ResponseEntity.ok(visitCount);
    }


    @RolesAllowed("ROLE_doctor")
    @GetMapping(path = "/doctor/monthlyVisitCount")
    public ResponseEntity<?> getMonthlyVisitCount(Principal principal) throws InvalidPrincipal {
        Long doctorId = Utilities.getUserDbIdFromPrincipal(principal);
        List<Integer> monthlyCount = service.getMonthlyVisitCount(doctorId);
        return ResponseEntity.ok(monthlyCount);
    }


    @RolesAllowed({"ROLE_doctor", "ROLE_patient"}) //kto moze zmienic date wizyty
    @PutMapping(path = "/visits/updateDate/{visitId}", produces = "application/json")
    public ResponseEntity<?> updateVisitDate(@PathVariable Long visitId, @RequestBody Map<String, String> newDate) {
        try {
            VisitDto visit = convertToDto(service.updateVisitDate(visitId, LocalDateTime.parse(newDate.get("newDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((visitNotFound.getLocalizedMessage()));
        }
    }

    @RolesAllowed("ROLE_doctor")
    @PutMapping(path = "/visits/updateLocation/{visitId}", produces = "application/json")
    public ResponseEntity<?> updateVisitLocation(@PathVariable Long visitId, @RequestBody Map<String, String> newLocation) {
        try {
            VisitDto visit = convertToDto(service.updateVisitLocation(visitId, newLocation.get("newLocation")));
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((visitNotFound.getLocalizedMessage()));
        }
    }

    @RolesAllowed("ROLE_doctor")
    @PutMapping(path = "/visits/updateDescription/{visitId}", produces = "application/json")
    public ResponseEntity<?> updateVisitDescription(@PathVariable Long visitId, @RequestBody Map<String, String> newDescription) {
        try {
            VisitDto visit = convertToDto(service.updateVisitDescription(visitId, newDescription.get("newDescription")));
            return ResponseEntity.ok(visit);
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((visitNotFound.getLocalizedMessage()));
        }
    }

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"}) //kto moze stworzyc wizyte
    @PostMapping(path = "/visits/create", produces = "application/json")
    public ResponseEntity<?> createVisit(@RequestBody CreateVisitDto createVisitDto,Principal principal) {
        try {
            Long patientId = Utilities.getUserDbIdFromPrincipal(principal);
            VisitDto visit = convertToDto(service.createVisit(createVisitDto.getDate(),
                    createVisitDto.getDescription(), createVisitDto.getLocation(),
                    createVisitDto.getDoctor_id(),
                    patientId));
            return ResponseEntity.ok(visit);
        } catch (UserNotFound | InvalidPrincipal userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((userNotFound.getLocalizedMessage()));
        }

    }

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"})
    @DeleteMapping(path = "/visits/id/{visitId}", produces = "application/json")
    public ResponseEntity<?> deleteVisit(@PathVariable Long visitId) {
        service.delete(visitId);
        return ResponseEntity.ok(visitId);
    }


    @RolesAllowed("ROLE_patient")
    @GetMapping(path = "/nextVisit/1")
    public ResponseEntity<?> getNextVisit(Principal principal) {
        try {
            Long patientId = Utilities.getUserDbIdFromPrincipal(principal);
            return ResponseEntity.ok(convertToDto(service.getNextVisit(patientId)));
        } catch (InvalidPrincipal invalidPrincipal) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", invalidPrincipal.getLocalizedMessage()));
        } catch (VisitNotFound visitNotFound) {
            return ResponseEntity.ok(0);    //no visits found
        }
    }

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"})
    @GetMapping(path = "/myVisits", produces = "application/json")
    public ResponseEntity<?> getUserVisits(Principal principal) {
        try {
            Long userId = Utilities.getUserDbIdFromPrincipal(principal);
            return ResponseEntity.ok(service.getUserVisits(userId).stream().map(this::convertToDto).collect(Collectors.toList()));
        } catch (InvalidPrincipal invalidPrincipal) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", invalidPrincipal.getLocalizedMessage()));
        }
    }

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"})
    @GetMapping(path="/available", produces = "application/json")
    public ResponseEntity<?> getAvailableHours(@RequestParam String startDate, @RequestParam String endDate) {
        return ResponseEntity.ok(service.getAvailableHours(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate)));
    }
}
