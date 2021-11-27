package com.example.backend.illness;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IllnessRepository extends CrudRepository<Illness, Long> {
    List<Illness> findAll();

    @Query(value = "select illness from Illness illness where illness.name like ?1")
    List<Illness> findAllByName(String illnessName);

    @Query(value = "select illness from Illness illness where illness.id = ?1")
    Optional<Illness> findById(Long id);
}
