package com.example.backend.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class VisitService {

    private  VisitRepository repository;

    @Autowired
    VisitService(VisitRepository repository) {this.repository = repository;}

    public List<Visit> getPatientVisits(Long patientId) {
        return ResponseEntity.ok(repository.findAllByPatientByPatientUserId(patientId)).getBody();
    }

    public List<Visit> getDoctorVisits(Long doctorId) {
        return ResponseEntity.ok(repository.findAllByDoctorByDoctorUserId(doctorId)).getBody();
    }

    public Visit updateVisitDate(Long visitId, Date newDate) throws ResourceNotFoundException {
        Visit visit = repository.findById(visitId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit not found on :: "+ visitId));
        visit.setDate(newDate);
        final Visit updatedVisit = repository.save(visit);
        return ResponseEntity.ok(updatedVisit).getBody();
    }
}
