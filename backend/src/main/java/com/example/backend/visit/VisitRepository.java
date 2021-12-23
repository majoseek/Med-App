package com.example.backend.visit;

import com.example.backend.doctor.Doctor;
import com.example.backend.patient.Patient;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Long> {
    List<Visit> findAllBy();

    List<Visit> findAllByPatientByPatientUserId(Patient patient);

    List<Visit> findAllByDoctorByDoctorUserId(Doctor doctor);

    List<Visit> findAllByDate(Date dateOfVisits);

    List<Visit> findAllByLocation(String location);
}
