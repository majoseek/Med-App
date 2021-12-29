package com.example.backend.medication;

import com.example.backend.medication.Medication;
import com.example.backend.exceptions.MedicationNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {
    final MedicationRepository repository;

    @Autowired
    MedicationService(MedicationRepository repository) {
        this.repository = repository;
    }

    public List<Medication> getAll() {
        return repository.findAll();
    }

    public Medication getById(Long id) throws MedicationNotFound{
        return repository.findById(id).orElseThrow(()->new MedicationNotFound("Could not find medication with id: " + id));
    }
}
