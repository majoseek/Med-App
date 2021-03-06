package com.example.backend.patient;

import com.example.backend.exceptions.IllnessNotFound;
import com.example.backend.exceptions.UserNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/patients")
public class PatientController {

    private final PatientService patientService;
    private final ModelMapper modelMapper;


    @Autowired
    public PatientController(PatientService patientService, ModelMapper modelMapper) {
        this.patientService = patientService;
        this.modelMapper = modelMapper;
    }

    private PatientDto convertToDto(Patient patient) {
        return modelMapper.map(patient, PatientDto.class);
    }

    @RolesAllowed("doctor")
    @GetMapping(path="/all", produces="application/json")
    public ResponseEntity<?> getAllPatients() {
        List<PatientDto> patientList = patientService.getAllPatients().stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(patientList);
    }

    @RolesAllowed("doctor")
    @GetMapping(path="/allByName", produces="application/json")
    public ResponseEntity<?> getPatientsByName(@RequestParam String name) {
        List<PatientDto> patientList = patientService.getPatientByName(name.toUpperCase()).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(patientList);
    }

    @RolesAllowed("ROLE_doctor")
    @GetMapping(path="/allByPesel", produces="application/json")
    public ResponseEntity<?> getPatientByPesel(@RequestParam String pesel) {
        if (pesel.length() != 11) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "PESEL must be 11 characters long"));
        }
        try {
            PatientDto patient = convertToDto(patientService.getPatientByPesel(pesel));
            return ResponseEntity.ok(patient);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @RolesAllowed("ROLE_doctor")
    @GetMapping(path="/{id}", produces="application/json")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        try {
            PatientDto patient = convertToDto(patientService.getPatientById(id));
            return ResponseEntity.ok(patient);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @RolesAllowed("ROLE_doctor")
    @GetMapping(path="/allByIllness/{illnessId}", produces="application/json")
    public ResponseEntity<?> getPatientByIllnessId(@PathVariable Long illnessId) {
        try {
            List<PatientDto> patients = patientService.getPatientsByIllnessId(illnessId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(patients);
        } catch (IllnessNotFound illnessNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", illnessNotFound.getLocalizedMessage()));
        }
    }
}
