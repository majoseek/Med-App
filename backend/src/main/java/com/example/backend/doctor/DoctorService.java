package com.example.backend.doctor;

import com.example.backend.exceptions.UserNotFound;
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

    List<Doctor> getAllDoctorsByNameOrSurname(String text) {
        return doctorRepository.findDoctorsByNames(text.toUpperCase());
    }

    public Doctor getDoctorById(Long id) throws UserNotFound {
        return doctorRepository.findById(id).orElseThrow(() -> new UserNotFound(String.format("User with id=%d does not exists", id)));
    }
}
