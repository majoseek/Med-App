package com.example.backend.prescription;

import com.example.backend.patient.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
    List<Prescription> findAllByPatientByPatientUserId(Long patientUserId);
}
