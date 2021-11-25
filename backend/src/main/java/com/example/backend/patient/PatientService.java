package com.example.backend.patient;

import com.example.backend.exceptions.UserNotFound;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAllBy();
    }

    public List<Patient> getPatientByName(String name) {
        return patientRepository.findPatientsByNames(name);
    }

    public Patient getPatientByPesel(String pesel) throws UserNotFound {
        return patientRepository.findPatientByPesel(pesel).orElseThrow(() -> new UserNotFound(String.format("Patient with PESEL=%s not found", pesel)));
    }

    public Patient getPatientById(Long id) throws UserNotFound {
        return patientRepository.findById(id).orElseThrow(() -> new UserNotFound(String.format("User with id=%d does not exists", id)));
    }
}
