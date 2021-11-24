package com.example.backend.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    List<Doctor> getListOfDoctors() {
        return doctorRepository.findAllBy();
    }

    List<Doctor> getAllDoctorsBySpecialization(String specialization) {
        return doctorRepository.findAllBySpecialization(specialization);
    }

}
