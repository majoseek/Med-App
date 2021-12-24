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

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;


    @Autowired
    VisitService(VisitRepository visitRepository,
                 PatientService patientService,
                 DoctorService doctorService) {
        this.visitRepository = visitRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public List<Visit> getAllVisits() {
        return visitRepository.findAllBy();
    }

    public Visit getVisitById(Long visitId) throws VisitNotFound{
        return visitRepository.findById(visitId).orElseThrow(()-> new VisitNotFound("Could not find visit " + visitId));
    }

    public List<Visit> getVisitsByDate(LocalDate dateOfVisits) {
        return visitRepository.findAllByDateBetween(dateOfVisits.atStartOfDay(), dateOfVisits.atTime(23, 59));
    }

    public List<Visit> getVisitsByLocation(String location) {
        return  visitRepository.findAllByLocation(location);
    }

    public List<Visit> getPatientVisits(Long patientId) throws UserNotFound {
        Patient patient = patientService.getPatientById(patientId);
        Hibernate.initialize(patient.getVisitsByUserId());
        return visitRepository.findAllByPatientByPatientUserId(patient);
    }

    public List<Visit> getDoctorVisits(Long doctorId) throws UserNotFound{
        Doctor doctor = doctorService.getDoctorById(doctorId);
        return visitRepository.findAllByDoctorByDoctorUserId(doctor);
    }

    public Visit updateVisitDate(Long visitId, LocalDateTime newDate) throws VisitNotFound {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new VisitNotFound("Visit not found on :: "+ visitId));
        visit.setDate(newDate);
        final Visit updatedVisit = visitRepository.save(visit);
        return ResponseEntity.ok(updatedVisit).getBody();
    }

    public Visit updateVisitLocation(Long visitId, String location) throws VisitNotFound {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new VisitNotFound("Visit not found on :: "+ visitId));
        visit.setLocation(location);
        final Visit updatedVisit = visitRepository.save(visit);
        return ResponseEntity.ok(updatedVisit).getBody();
    }

    public Visit updateVisitDescription(Long visitId, String description) throws VisitNotFound {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new VisitNotFound("Visit not found on :: "+ visitId));
        visit.setDescription(description);
        final Visit updatedVisit = visitRepository.save(visit);
        return ResponseEntity.ok(updatedVisit).getBody();
    }

    public Visit save(Visit newVisit) {
        return visitRepository.save(newVisit);
    }

    public void delete(Long visitId){
        visitRepository.deleteById(visitId);
    }

    public Visit createVisit(LocalDateTime date, String description, String location, Long doctorId, Long patientId) throws UserNotFound {
        Visit newVisit = new Visit();
        Doctor doctor = doctorService.getDoctorById(doctorId);
        Patient patient = patientService.getPatientById(patientId);
        newVisit.setDate(date);
        newVisit.setDescription(description);
        newVisit.setLocation(location);
        newVisit.setDoctorByDoctorUserId(doctor);
        newVisit.setPatientByPatientUserId(patient);
        visitRepository.save(newVisit);
        return newVisit;
    }


}
