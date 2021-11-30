package com.example.backend.prescription;

import com.example.backend.doctor.Doctor;
import com.example.backend.exceptions.PrescriptionNotFound;
import com.example.backend.patient.Patient;
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
        return repository.findAllByPatientByPatientUserId(patientId);
    }

    public List<Prescription> getDoctorPrescription(Long doctorId) {
        return repository.findAllByDoctorByDoctorUserId(doctorId);
    }

    public Prescription getPrescriptionById(Long prescriptionId) throws PrescriptionNotFound {
        return repository.findById(prescriptionId).orElseThrow(()-> new PrescriptionNotFound("Could not find prescription " + prescriptionId));
    }

    public Prescription save(Prescription newPrescription) {
        return repository.save(newPrescription);
    }

    public void delete(Long prescriptionId){
        if(repository.existsById(prescriptionId)) {
            repository.deleteById(prescriptionId);
        }
    }


    public Prescription createPrescription(String medicationName, Long amount, Doctor doctor, Patient patient) {
        final Prescription newPrescription = new Prescription();
        newPrescription.setMedicationName(medicationName);
        newPrescription.setAmount(amount);
        newPrescription.setDoctorByDoctorUserId(doctor);
        newPrescription.setPatientByPatientUserId(patient);
        repository.save(newPrescription);
        return newPrescription;
    }
}
