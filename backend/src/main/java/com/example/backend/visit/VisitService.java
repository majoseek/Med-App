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
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

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

    public Visit editVisitData(Long visitId, Optional<String> dateString, Optional<String> description, Optional<String> location, Optional<Doctor> doctor, Optional<Patient> patient) throws VisitNotFound {
        Visit visit = repository.findById(visitId)
                .orElseThrow(() -> new VisitNotFound("Visit not found on :: "+ visitId));
        if(dateString.isPresent()){
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(format.parse(dateString.get()).getTime());
                visit.setDate(date);
            } catch (ParseException parseException) {} // cos tu powinno byc?
        }
        description.ifPresent(visit::setDescription);
        location.ifPresent(visit::setLocation);
        doctor.ifPresent(visit::setDoctorByDoctorUserId);
        patient.ifPresent(visit::setPatientByPatientUserId);
        repository.save(visit);
        return getVisitById(visitId);
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
