package com.example.backend.illness;

import com.example.backend.patient.Patient;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IllnessRepository extends CrudRepository<Illness, Long> {
    @NotNull List<Illness> findAll();

    List<Illness> findAllByName(String illnessName);

    @NotNull Optional<Illness> findById(@NotNull Long id);

    List<Illness> findAllByPatientsByIllnessIdIn(Collection<Patient> patientsByIllnessId);
}
