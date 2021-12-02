package com.example.backend.prescription;


import com.example.backend.doctor.Doctor;
import com.example.backend.exceptions.PrescriptionNotFound;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.patient.Patient;
import com.example.backend.patient.PatientDto;
import com.example.backend.visit.Visit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PrescriptionController {

    private final PrescriptionService service;
    private final ModelMapper modelMapper;

    @Autowired
    public PrescriptionController(PrescriptionService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    private PrescriptionDto convertToDto(Prescription prescription) {
        return modelMapper.map(prescription, PrescriptionDto.class);
    }

    @GetMapping("/{patientId}/prescriptions")
    @ResponseBody
    public ResponseEntity<?> getPatientPrescription(@PathVariable Long patientId) {
        List<PrescriptionDto> prescriptions = service.getPatientPrescription(patientId).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/{doctorId}/prescription")
    @ResponseBody
    public ResponseEntity<?> getDoctorPrescription(@PathVariable Long doctorId) {
        List<PrescriptionDto> prescriptions = service.getDoctorPrescription(doctorId).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/prescriptions/{prescriptionId}")
    @ResponseBody
    public ResponseEntity<?> getPrescriptionById(@PathVariable Long prescriptionId) {
        try {
            PrescriptionDto prescription = convertToDto(service.getPrescriptionById(prescriptionId));
            return ResponseEntity.ok(prescription);
        } catch (PrescriptionNotFound prescriptionNotFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((prescriptionNotFound.getLocalizedMessage()));
        }
    }

    @PostMapping("/prescriptions")
    public ResponseEntity<?> createPrescription(@RequestBody CreatePrescriptionDto prescriptionDto) {
        try {
            PrescriptionDto prescription = convertToDto(service.createPrescription(prescriptionDto));
            return ResponseEntity.ok(prescription);
        }catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", userNotFound.getLocalizedMessage()));
        }
    }

    @PostMapping("/prescriptionByPesel")
    public ResponseEntity<?> createByPatientPesel(@RequestBody CreateByPeselPrescriptionDto prescriptionDto) {
        try{
            PrescriptionDto prescription = convertToDto(service.createByPatientPesel(prescriptionDto));
            return ResponseEntity.ok(prescription);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((userNotFound.getLocalizedMessage()));
        }

    }

    @DeleteMapping("/prescriptions/{prescriptionId}")
    public ResponseEntity<?> deletePrescription(
            @PathVariable Long prescriptionId) {
        service.delete(prescriptionId);
        return ResponseEntity.ok(prescriptionId);}
}
