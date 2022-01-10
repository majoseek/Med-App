package com.example.backend.visit;

import com.example.backend.doctor.Doctor;
import com.example.backend.patient.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Long> {
    List<Visit> findAllBy();

    List<Visit> findAllByPatientByPatientUserId(Patient patient);

    List<Visit> findAllByDoctorByDoctorUserId(Doctor doctor);

    List<Visit> findAllByDateBetween(LocalDateTime start, LocalDateTime end);

    List<Visit> findAllByLocation(String location);

    @Query(value = "select count(v) from Visit v where v.doctorByDoctorUserId.userId = :doctorId and year(v.date) = year(sysdate())")
    Integer countVisitByMonthAndDoctor(Long doctorId);

    @Query(value="select v from Visit v where v.doctorByDoctorUserId.userId = :userId or v.patientByPatientUserId.userId = :userId")
    List<Visit> findAllByUserId(Long userId);
}
