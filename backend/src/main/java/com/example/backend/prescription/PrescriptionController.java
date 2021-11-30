package com.example.backend.prescription;


import com.example.backend.doctor.Doctor;
import com.example.backend.exceptions.PrescriptionNotFound;
import com.example.backend.patient.Patient;
import com.example.backend.visit.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrescriptionController {

    private PrescriptionService service;

    @Autowired
    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @GetMapping("/{patientId}/prescriptions")
    @ResponseBody
    public ResponseEntity<?> getPatientPrescription(@PathVariable Long patientId) {
        List<Prescription> prescriptions = service.getPatientPrescription(patientId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/{doctorId}/prescription")
    @ResponseBody
    public ResponseEntity<?> getDoctorPrescription(@PathVariable Long doctorId) {
        List<Prescription> prescriptions = service.getDoctorPrescription(doctorId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/prescriptions/{prescriptionId}")
    @ResponseBody
    public ResponseEntity<?> getPrescriptionById(@PathVariable Long prescriptionId) {
        try {
            Prescription prescription = service.getPrescriptionById(prescriptionId);
            return ResponseEntity.ok(prescription);
        } catch (PrescriptionNotFound prescriptionNotFound){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((prescriptionNotFound.getLocalizedMessage()));
        }
    }

    @PostMapping("/prescriptions")
    public ResponseEntity<?> createPrescription(@RequestBody String medicationName,
                                                @RequestBody Long amount,
                                                @RequestBody Doctor doctor,
                                                @RequestBody Patient patient) {
        Prescription prescription = service.createPrescription(medicationName, amount, doctor, patient);
        return ResponseEntity.ok(prescription);
    }

    @DeleteMapping("/prescriptions/{prescriptionId}")
    public ResponseEntity<?> deletePrescription(
            @PathVariable Long prescriptionId) {
        service.delete(prescriptionId);
        return ResponseEntity.ok(prescriptionId);}
}
