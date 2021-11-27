package com.example.backend.visit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends CrudRepository<Visit, Long> {
    List<Visit> findAll();

    List<Visit> findAllByPatientByPatientUserId(Long patientId);

    List<Visit> findAllByDoctorByDoctorUserId(Long doctorId);

    List<Visit> findAllByDate(Date dateOfVisits);

    List<Visit> findAllByLocation(String location);
}
