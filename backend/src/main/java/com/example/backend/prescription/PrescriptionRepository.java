package com.example.backend.prescription;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

    @Query(value = "select p from Prescription p where p.patientByPatientUserId.userId = :patientUserId")
    List<Prescription> findAllByPatientId(Long patientUserId);

    @Query(value = "select p from Prescription p where p.doctorByDoctorUserId.userId = :doctorId")
    List<Prescription> findAllByDoctorByDoctorUserId(Long doctorId);

    @Query(value = "select p from Prescription p where p.doctorByDoctorUserId.userId = :userId or p.patientByPatientUserId.userId = :userId")
    List<Prescription> findAllByUserId(Long userId);
}
