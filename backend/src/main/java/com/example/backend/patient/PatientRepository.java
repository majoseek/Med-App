package com.example.backend.patient;

import com.example.backend.illness.Illness;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient> findAllBy();

    @Query(value = "select patient from Patient patient where upper(patient.name) like %:text% or upper(patient.surname) like %:text%")
    List<Patient> findPatientsByNames(@Param(value = "text") String text);

    Optional<Patient> findPatientByPesel(String pesel);


    List<Patient> getAllByIllnessesByUserIdIn(Collection<Illness> illnessesByUserId);
}
