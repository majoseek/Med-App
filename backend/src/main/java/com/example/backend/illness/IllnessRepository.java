package com.example.backend.illness;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IllnessRepository extends CrudRepository<Illness, Long> {
    List<Illness> findAll();

    List<Illness> findAllByName(String illnessName);

    Optional<Illness> findById(Long id);
}
