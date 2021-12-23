package com.example.backend.illness;

import com.example.backend.exceptions.IllnessNotFound;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.patient.Patient;
import com.example.backend.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IllnessService {

    private final IllnessRepository illnessRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public IllnessService(IllnessRepository illnessRepository, PatientRepository patientRepository){
        this.illnessRepository = illnessRepository;
        this.patientRepository = patientRepository;
    }

    public List<Illness> getAllIllnesses() {
        return illnessRepository.findAll();
    }

    public List<Illness> getIllnessByName(String illnessName) {
        return illnessRepository.findAllByName(illnessName);
    }

    public Illness getIllnessById(Long illnessId) throws IllnessNotFound {
        return illnessRepository.findById(illnessId).orElseThrow(()->new IllnessNotFound("Could not find illness of id "+illnessId));
    }

    public List<Illness> getIllnessByPatientId(Long patientId) throws UserNotFound {
        return illnessRepository.findAllByPatientsByIllnessIdIn(List.of(
                patientRepository.findById(patientId).orElseThrow(
                        () -> new UserNotFound("Could not find user with id "+patientId)
                ))
        );
    }


    public Illness addPatientIllness(Long patientId, Long illnessId) throws UserNotFound, IllnessNotFound {
        //check whether illness and patient exist
        Illness illness = illnessRepository.findById(illnessId).orElseThrow(() -> new IllnessNotFound("Could not find illness of id "+illnessId));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new UserNotFound("Could not find user with id "+patientId));
        patient.addPatientsIllness(List.of(illness));
        patientRepository.save(patient);
        return illness;
    }
}
