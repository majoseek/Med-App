package com.example.backend.visit;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VisitRepository extends CrudRepository<Visit, Long> {


    List<Visit> findAllByPatientByPatientUserId(Long patientId);

    List<Visit> findAllByDoctorByDoctorUserId(Long doctorId);
}
