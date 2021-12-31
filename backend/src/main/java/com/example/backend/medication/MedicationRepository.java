package com.example.backend.medication;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicationRepository extends CrudRepository<Medication, Long> {
    @Query(value = "select m from Medication m")
    @NotNull
    List<Medication> findAll();
}
