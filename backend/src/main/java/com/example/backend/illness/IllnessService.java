package com.example.backend.illness;

import com.example.backend.exceptions.IllnessNotFound;
import com.example.backend.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IllnessService {

    private final IllnessRepository repository;

    @Autowired
    public IllnessService(IllnessRepository repository){this.repository = repository;}

    public List<Illness> getAllIllnesses() {
        return repository.findAll();
    }

    public List<Illness> getIllnessByName(String illnessName) {
        return repository.findAllByName(illnessName);
    }

    public Illness getIllnessById(Long illnessId) throws IllnessNotFound {
        return repository.findById(illnessId).orElseThrow(()->new IllnessNotFound("Could not find illness of id "+illnessId));
    }

    public Illness editIllnessName(Long illnessId, String name) throws IllnessNotFound {
        final Illness illness = repository.findById(illnessId).orElseThrow(
                ()->new IllnessNotFound(String.format("Illness with id=%d does not exist", illnessId))
        );
        illness.setName(name);
        repository.save(illness);
        return getIllnessById(illnessId);
    }
}
