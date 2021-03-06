package com.example.backend.illness;

import com.example.backend.exceptions.IllnessNotFound;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.patient.Patient;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/illnesses")
public class IllnessController {
    private final IllnessService service;
    private final ModelMapper modelMapper;

    @Autowired
    public IllnessController(IllnessService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    private IllnessDto convertToDto(Illness illness) {
        return this.modelMapper.map(illness, IllnessDto.class);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<?> getAllIllnesses() {
        List<IllnessDto> illnesses = service.getAllIllnesses().stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(illnesses);
    }

    @GetMapping("/name/{illnessName}")
    @ResponseBody
    public ResponseEntity<?> getIllnessByName(@PathVariable String illnessName) {
        List<IllnessDto> illnesses = service.getIllnessByName(illnessName).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(illnesses);
    }

    @GetMapping("/id/{illnessId}")
    @ResponseBody
    public ResponseEntity<?> getIllnessById(@PathVariable Long illnessId) {
        try{
            IllnessDto illness = convertToDto(service.getIllnessById(illnessId));
            return ResponseEntity.ok(illness);
        } catch (IllnessNotFound illnessNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(illnessNotFound.getLocalizedMessage());
        }
    }

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"})
    @GetMapping("/byPatient/{patientId}")
    @ResponseBody
    public ResponseEntity<?> getIllnessByPatientId(@PathVariable Long patientId) {
        try {
            List<IllnessDto> illnesses = service.getIllnessByPatientId(patientId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(illnesses);
        } catch(UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", userNotFound.getLocalizedMessage()));
        }
    }

    @RolesAllowed("ROLE_doctor")
    @Transactional
    @PutMapping("/{patientId}")
    @ResponseBody
    public ResponseEntity<?> addPatientIllness(@PathVariable Long patientId,
                                               @RequestBody Map.Entry<String, Long> illnessId) {
        try {
            IllnessDto illness = convertToDto(service.addPatientIllness(patientId, illnessId.getValue()));
            return ResponseEntity.ok(illness);
        } catch (UserNotFound | IllnessNotFound notFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound.getLocalizedMessage());
        }
    }

    @RolesAllowed("ROLE_doctor")
    @Transactional
    @PutMapping("/update/{illnessId}")
    @ResponseBody
    public ResponseEntity<?> editIllnessName(@PathVariable Long illnessId,
                                             @RequestParam String name) {
        try {
            final IllnessDto illness = convertToDto(service.editIllnessName(illnessId, name));
            return ResponseEntity.ok(illness);
        } catch (IllnessNotFound illnessNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(illnessNotFound.getLocalizedMessage());
        }
    }
}
