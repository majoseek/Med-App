package com.example.backend.illness;

import com.example.backend.exceptions.IllnessNotFound;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.patient.Patient;
import com.example.backend.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IllnessMapService {

    private final IllnessMapRepository repository;
    private final IllnessService illnessService;
    private final PatientService patientService;

    @Autowired
    IllnessMapService(IllnessMapRepository repository,
                      IllnessService illnessService,
                      PatientService patientService) {
        this.repository=repository;
        this.illnessService=illnessService;
        this.patientService=patientService;
    }

    public List<Illness> getIllnessByPatientId(Long patientId) {
        return repository.findAllByPatientId(patientId);
    }

    public List<Patient> getPatientByIllnessId(Long illnessId) {
        return repository.findAllByIllnessId(illnessId);
    }

    public IllnessMap addPatientIllness(Long patientId, Long illnessId) throws UserNotFound, IllnessNotFound {
        //check whether illness and patient exist
        illnessService.getIllnessById(illnessId);
        patientService.getPatientById(patientId);

        final IllnessMap illnessMap = new IllnessMap();
        illnessMap.setIllnessId(illnessId);
        illnessMap.setPatientUserId(patientId);
        repository.save(illnessMap);
        return illnessMap;
    }
}
