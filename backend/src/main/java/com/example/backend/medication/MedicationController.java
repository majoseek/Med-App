package com.example.backend.medication;

import com.example.backend.exceptions.MedicationNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping("/medications")
public class MedicationController {
    private final MedicationService service;
    private final ModelMapper mapper;

    @Autowired
    public MedicationController(MedicationService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    private MedicationDto convertToDto(Medication medication) {

        return mapper.map(medication, MedicationDto.class);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<?> getAll() {
        List<MedicationDto> medications = service.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(medications);
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            MedicationDto medications = convertToDto(service.getById(id));
            return ResponseEntity.ok(medications);
        } catch (MedicationNotFound medicationNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(medicationNotFound.getLocalizedMessage());
        }
    }
}
