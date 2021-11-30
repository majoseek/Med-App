package com.example.backend.illness;

import com.example.backend.exceptions.IllnessNotFound;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/illnesses")
public class IllnessMapController {

    private final IllnessMapService service;

    @Autowired
    IllnessMapController(IllnessMapService service) {
        this.service = service;
    }

    @GetMapping("/{patientId}")
    @ResponseBody
    public ResponseEntity<?> getIllnessByPatientId(@PathVariable Long patientId) {
        List<Illness> illnesses = service.getIllnessByPatientId(patientId);
        return ResponseEntity.ok(illnesses);
    }

    @GetMapping("/{illnessId}")
    @ResponseBody
    public ResponseEntity<?> getPatientByIllnessId(@PathVariable Long illnessId) {
        List<Patient> patients = service.getPatientByIllnessId(illnessId);
        return ResponseEntity.ok(patients);
    }

    @PutMapping("/{patientId}")
    @ResponseBody
    public ResponseEntity<?> addPatientIllness(@PathVariable Long patientId,
                                               @RequestBody Long illenessId) {
        try {
            IllnessMap illnessMap = service.addPatientIllness(patientId, illenessId);
            return ResponseEntity.ok(illnessMap);
        } catch (UserNotFound | IllnessNotFound notFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound.getLocalizedMessage());
        }
    }
}