package com.example.backend.visit;

import com.example.backend.exceptions.UserNotFound;
import com.example.backend.exceptions.VisitNotFound;
import com.example.backend.patient.PatientController;
import com.example.backend.patient.PatientRepository;
import com.example.backend.patient.PatientService;
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

    public Visit getVisitById(Long visitId) throws VisitNotFound{
        return repository.findById(visitId).orElseThrow(()-> new VisitNotFound("Could not find visit " + visitId));
    }

    public List<Visit> getVisitsByDate(Date dateOfVisits) {
        return repository.findAllByDate(dateOfVisits);
    }

    public List<Visit> getVisitsByLocation(String location) {
        return  repository.findAllByLocation(location);
    }

    public List<Visit> getPatientVisits(Long patientId) throws UserNotFound { //jak rzucic wyjatkiem?
        return repository.findAllByPatientByPatientUserId(patientId);
    }

    public List<Visit> getDoctorVisits(Long doctorId) throws UserNotFound{ //jak rzucic wyjatkiem?
        return repository.findAllByDoctorByDoctorUserId(doctorId);
    }

    public Visit updateVisitDate(Long visitId, Date newDate) throws ResourceNotFoundException {
        Visit visit = repository.findById(visitId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit not found on :: "+ visitId));
        visit.setDate(newDate);
        final Visit updatedVisit = repository.save(visit);
        return ResponseEntity.ok(updatedVisit).getBody();
    }

    public Visit save(Visit newVisit) {
        return repository.save(newVisit);
    }

    public boolean delete(Long visitId) {
        if(repository.existsById(visitId)) {
            repository.deleteById(visitId);
            return true;
        }
        else
            return false;
    }


}
