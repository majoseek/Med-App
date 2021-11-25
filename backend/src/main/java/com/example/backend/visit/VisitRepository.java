package com.example.backend.visit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends CrudRepository<Visit, Long> {
    List<Visit> findAll();

    @Query(value = "select visit from Visit visit where visit.patientByPatientUserId = ?1")
    List<Visit> findAllByPatientByPatientUserId(Long patientId);

    @Query(value = "select visit from Visit visit where visit.doctorByDoctorUserId = ?1")
    List<Visit> findAllByDoctorByDoctorUserId(Long doctorId);

    @Query(value = "select visit from Visit visit where visit.date = ?1")
    List<Visit> findAllByDate(Date dateOfVisits);

    @Query(value = "select visit from Visit visit where visit.location = ?1")
    List<Visit> findAllByLocation(String location);
}
