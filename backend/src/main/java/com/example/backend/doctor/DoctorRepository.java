package com.example.backend.doctor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    @Query(value = "select doc from Doctor doc where upper(doc.name) like %:text% or upper(doc.surname) like %:text%")
    List<Doctor> findDoctorsByNames(@Param(value = "text") String text);

    List<Doctor> findAllBy();

    List<Doctor> findAllBySpecializationIgnoreCase(String specialization);

    List<Doctor> getAllBy();
}
