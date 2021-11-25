package com.example.backend.prescription;

import com.example.backend.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private PrescriptionRepository repository;

    @Autowired
    PrescriptionService(PrescriptionRepository repository) {this.repository = repository;}

    public List<Prescription> getPatientPrescription (Long patientId) {
        return ResponseEntity.ok(repository.findAllByPatientByPatientUserId(patientId)).getBody();
    }

    public Prescription save(Prescription newPrescription) {
        return repository.save(newPrescription);
    }

    public boolean delete(Long prescriptionId){
        if(repository.existsById(prescriptionId)) {
            repository.deleteById(prescriptionId);
            return true;
        }
        else
            return false;
    }

}
