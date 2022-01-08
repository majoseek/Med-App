package com.example.backend.prescription;

import com.example.backend.exceptions.InvalidPrincipal;
import com.example.backend.exceptions.PrescriptionNotFound;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.medication.Medication;
import com.example.backend.medication.MedicationDto;
import com.example.backend.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping(path="/prescriptions")
public class PrescriptionController {

    private final PrescriptionService service;
    private final ModelMapper mapper;

    private MedicationDto convertMedToDto(Medication med) {
        return mapper.map(med, MedicationDto.class);
    }


    @Autowired
    public PrescriptionController(PrescriptionService service, ModelMapper modelMapper) {
        this.service = service;
        this.mapper = modelMapper;
        this.mapper.typeMap(Prescription.class, PrescriptionDto.class)
                .addMappings(m -> m.using(ctx -> ((Collection<Medication>) ctx.getSource()).stream().map(this::convertMedToDto).collect(Collectors.toList()))
                        .map(Prescription::getMedicationsByPrescriptId, PrescriptionDto::setMedicationDto));
    }

    private PrescriptionDto convertToDto(Prescription prescription) {
        return mapper.map(prescription, PrescriptionDto.class);
    }

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"})
    @GetMapping(path="/allByPatient/{patientId}", produces="application/json")
    public ResponseEntity<?> getPatientPrescription(@PathVariable Long patientId) {
        List<PrescriptionDto> prescriptions = service.getPatientPrescription(patientId).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(prescriptions);
    }

    @RolesAllowed({"ROLE_doctor"})
    @GetMapping(path="/allByDoctor/{doctorId}", produces = "application/json")
    public ResponseEntity<?> getDoctorPrescription(@PathVariable Long doctorId) {
        try {
            List<PrescriptionDto> prescriptions = service.getDoctorPrescription(doctorId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(prescriptions);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((userNotFound.getLocalizedMessage()));
        }
    }

    @GetMapping(path="/id/{prescriptionId}", produces = "application/json")
    public ResponseEntity<?> getPrescriptionById(@PathVariable Long prescriptionId) {
        try {
            PrescriptionDto prescription = convertToDto(service.getPrescriptionById(prescriptionId));
            return ResponseEntity.ok(prescription);
        } catch (PrescriptionNotFound prescriptionNotFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((prescriptionNotFound.getLocalizedMessage()));
        }
    }

    @RolesAllowed("ROLE_doctor")
    @PostMapping(path="/create", produces = "application/json")
    public ResponseEntity<?> createPrescription(@RequestBody CreatePrescriptionDto prescriptionDto) {
        try {
            PrescriptionDto prescription = convertToDto(service.createPrescription(prescriptionDto));
            return ResponseEntity.ok(prescription);
        }catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", userNotFound.getLocalizedMessage()));
        }
    }

    @RolesAllowed("ROLE_doctor")
    @PostMapping(path="/createByPesel", produces = "application/json")
    public ResponseEntity<?> createByPatientPesel(@RequestBody CreateByPeselPrescriptionDto prescriptionDto) {
        try{
            PrescriptionDto prescription = convertToDto(service.createByPatientPesel(prescriptionDto));
            return ResponseEntity.ok(prescription);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((userNotFound.getLocalizedMessage()));
        }

    }

    @RolesAllowed("ROLE_doctor")
    @DeleteMapping(path="/{prescriptionId}", produces = "application/json")
    public ResponseEntity<?> deletePrescription(
            @PathVariable Long prescriptionId) {
        service.delete(prescriptionId);
        return ResponseEntity.ok(prescriptionId);}

    @RolesAllowed({"ROLE_doctor", "ROLE_patient"})
    @GetMapping(path="/myPrescriptions", produces = "application/json")
    public ResponseEntity<?> getUserPrescriptions(Principal principal) {
        try {
            Long userId = Utilities.getUserDbIdFromPrincipal(principal);
            return ResponseEntity.ok(service.getUserPrescriptions(userId).stream().map(this::convertToDto).collect(Collectors.toList()));
        } catch (InvalidPrincipal invalidPrincipal) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", invalidPrincipal.getLocalizedMessage()));
        }
    }
}
