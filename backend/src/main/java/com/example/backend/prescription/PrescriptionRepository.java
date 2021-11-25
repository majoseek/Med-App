package com.example.backend.prescription;

import com.example.backend.patient.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

    @Query(value = "select prescription from Prescription prescription where prescription.patientByPatientUserId = ?1")
    List<Prescription> findAllByPatientByPatientUserId(Long patientUserId);

    @Query(value = "select prescription from Prescription prescription where prescription.doctorByDoctorUserId = ?1")
    List<Prescription> findAllByDoctorByDoctorUserId(Long doctorId);
}
