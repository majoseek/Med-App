package com.example.backend.patient;

import com.example.backend.exceptions.IllnessNotFound;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.illness.IllnessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final IllnessRepository illnessRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, IllnessRepository illnessRepository) {
        this.patientRepository = patientRepository;
        this.illnessRepository = illnessRepository;
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

    public List<Patient> getPatientsByIllnessId(Long illnessId) throws IllnessNotFound {
        return patientRepository.getAllByIllnessesByUserIdIn(List.of(
                illnessRepository.findById(illnessId).orElseThrow(
                        () -> new IllnessNotFound("Could not find illness of id "+illnessId)
                ))
        );
    }
}
