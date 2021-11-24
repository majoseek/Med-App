package com.example.backend.doctor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    List<Doctor> findAllBy();

    List<Doctor> findAllBySpecialization(String specialization);

}
