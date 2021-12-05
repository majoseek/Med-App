package com.example.backend.visit;

import com.example.backend.doctor.Doctor;
import com.example.backend.doctor.DoctorService;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.exceptions.VisitNotFound;
import com.example.backend.patient.Patient;
import com.example.backend.patient.PatientService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class VisitService {

    private final VisitRepository repository;
    private final PatientService patientService;
    private final DoctorService doctorService;


    @Autowired
    VisitService(VisitRepository repository,
                 PatientService patientService,
                 DoctorService doctorService) {
        this.repository = repository;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public List<Visit> getAllVisits() {
        return repository.findAllBy();
    }

    public Visit getVisitById(Long visitId) throws VisitNotFound{
        return repository.findById(visitId).orElseThrow(()-> new VisitNotFound("Could not find visit " + visitId));
    }

    public List<Visit> getVisitsByDate(Date dateOfVisits) {
        return repository.findAllByDate(dateOfVisits);
    }

    public List<Visit> getVisitsByLocation(String location) {
        return  repository.findAllByLocation(location);
    }

    public List<Visit> getPatientVisits(Long patientId) throws UserNotFound {
        Patient patient = patientService.getPatientById(patientId);
        Hibernate.initialize(patient.getVisitsByUserId());
        return repository.findAllByPatientByPatientUserId(patient);
    }

    public List<Visit> getDoctorVisits(Long doctorId) throws UserNotFound{
        Doctor doctor = doctorService.getDoctorById(doctorId);
        return repository.findAllByDoctorByDoctorUserId(doctor);
    }

    public Visit updateVisitDate(Long visitId, Date newDate) throws VisitNotFound {
        Visit visit = repository.findById(visitId)
                .orElseThrow(() -> new VisitNotFound("Visit not found on :: "+ visitId));
        visit.setDate(newDate);
        final Visit updatedVisit = repository.save(visit);
        return ResponseEntity.ok(updatedVisit).getBody();
    }

    public Visit save(Visit newVisit) {
        return repository.save(newVisit);
    }

    public void delete(Long visitId){
        repository.deleteById(visitId);
    }

    public Visit createVisit(Date date, String description, String location, Doctor doctor, Patient patient) {
        Visit newVisit = new Visit();
        newVisit.setDate(date);
        newVisit.setDescription(description);
        newVisit.setLocation(location);
        newVisit.setDoctorByDoctorUserId(doctor);
        newVisit.setPatientByPatientUserId(patient);
        repository.save(newVisit);
        return newVisit;
    }


}
