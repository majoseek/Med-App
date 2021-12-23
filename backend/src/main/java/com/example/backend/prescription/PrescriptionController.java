package com.example.backend.prescription;


import com.example.backend.doctor.Doctor;
import com.example.backend.exceptions.PrescriptionNotFound;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.medication.Medication;
import com.example.backend.medication.MedicationDto;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping("/prescriptions")
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

    @GetMapping("/allByPatient/{patientId}")
    @ResponseBody
    public ResponseEntity<?> getPatientPrescription(@PathVariable Long patientId) {
        try{
            List<PrescriptionDto> prescriptions = service.getPatientPrescription(patientId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(prescriptions);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((userNotFound.getLocalizedMessage()));
        }
    }

    @GetMapping("/allByDoctor/{doctorId}")
    @ResponseBody
    public ResponseEntity<?> getDoctorPrescription(@PathVariable Long doctorId) {
        try {
            List<PrescriptionDto> prescriptions = service.getDoctorPrescription(doctorId).stream().map(this::convertToDto).collect(Collectors.toList());
            return ResponseEntity.ok(prescriptions);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((userNotFound.getLocalizedMessage()));
        }
    }

    @GetMapping("/id/{prescriptionId}")
    @ResponseBody
    public ResponseEntity<?> getPrescriptionById(@PathVariable Long prescriptionId) {
        try {
            PrescriptionDto prescription = convertToDto(service.getPrescriptionById(prescriptionId));
            return ResponseEntity.ok(prescription);
        } catch (PrescriptionNotFound prescriptionNotFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((prescriptionNotFound.getLocalizedMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPrescription(@RequestBody CreatePrescriptionDto prescriptionDto) {
        try {
            PrescriptionDto prescription = convertToDto(service.createPrescription(prescriptionDto));
            return ResponseEntity.ok(prescription);
        }catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", userNotFound.getLocalizedMessage()));
        }
    }

    @PostMapping("/createByPesel")
    public ResponseEntity<?> createByPatientPesel(@RequestBody CreateByPeselPrescriptionDto prescriptionDto) {
        try{
            PrescriptionDto prescription = convertToDto(service.createByPatientPesel(prescriptionDto));
            return ResponseEntity.ok(prescription);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((userNotFound.getLocalizedMessage()));
        }

    }

    @DeleteMapping("/{prescriptionId}")
    public ResponseEntity<?> deletePrescription(
            @PathVariable Long prescriptionId) {
        service.delete(prescriptionId);
        return ResponseEntity.ok(prescriptionId);}
}
