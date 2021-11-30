package com.example.backend.illness;


import com.example.backend.patient.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IllnessMapRepository extends CrudRepository<IllnessMap, IllnessMapPK> {

    @Query(value = "SELECT illness FROM Illness illness\n" +
            "                  INNER JOIN IllnessMap illnessMap \n" +
            "                             ON illness.id = illnessMap.illnessId\n" +
            "WHERE illnessMap.patientUserId = ?1")
    List<Illness> findAllByPatientId(Long patientId);

    @Query(value = "SELECT patient FROM Patient patient\n" +
            "                  INNER JOIN IllnessMap illnessMap \n" +
            "                             ON patient.userId = illnessMap.patientUserId\n" +
            "WHERE illnessMap.illnessId = ?1")
    List<Patient> findAllByIllnessId(Long illnessId);
}
