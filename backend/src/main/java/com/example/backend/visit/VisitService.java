package com.example.backend.visit;

import com.example.backend.doctor.Doctor;
import com.example.backend.doctor.DoctorService;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.exceptions.VisitNotFound;
import com.example.backend.patient.Patient;
import com.example.backend.patient.PatientService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Visit getVisitById(Long visitId) throws VisitNotFound {
        return visitRepository.findById(visitId).orElseThrow(() -> new VisitNotFound("Could not find visit " + visitId));
    }

    public List<Visit> getVisitsByDate(LocalDate dateOfVisits) {
        return visitRepository.findAllByDateBetween(dateOfVisits.atStartOfDay(), dateOfVisits.plusDays(8).atStartOfDay());
    }

    public List<Visit> getVisitsByLocation(String location) {
        return visitRepository.findAllByLocation(location);
    }

    public List<Visit> getPatientVisits(Long patientId) throws UserNotFound {
        Patient patient = patientService.getPatientById(patientId);
        Hibernate.initialize(patient.getVisitsByUserId());
        return visitRepository.findAllByPatientByPatientUserId(patient);
    }

    public List<Visit> getDoctorVisits(Long doctorId) throws UserNotFound {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        return visitRepository.findAllByDoctorByDoctorUserId(doctor);
    }

    public Visit updateVisitDate(Long visitId, LocalDateTime newDate) throws VisitNotFound {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new VisitNotFound("Visit not found on :: " + visitId));
        visit.setDate(newDate);
        final Visit updatedVisit = visitRepository.save(visit);
        return ResponseEntity.ok(updatedVisit).getBody();
    }

    public Visit updateVisitLocation(Long visitId, String location) throws VisitNotFound {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new VisitNotFound("Visit not found on :: " + visitId));
        visit.setLocation(location);
        final Visit updatedVisit = visitRepository.save(visit);
        return ResponseEntity.ok(updatedVisit).getBody();
    }

    public Visit updateVisitDescription(Long visitId, String description) throws VisitNotFound {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new VisitNotFound("Visit not found on :: " + visitId));
        visit.setDescription(description);
        final Visit updatedVisit = visitRepository.save(visit);
        return ResponseEntity.ok(updatedVisit).getBody();
    }

    public Visit save(Visit newVisit) {
        return visitRepository.save(newVisit);
    }

    public void delete(Long visitId) {
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

    public List<Visit> getUserVisits(Long userId) {
        return visitRepository.findAllByUserId(userId);
    }


    public Integer getVisitCountByMonth(Long doctorId) {
        return visitRepository.countVisitByMonthAndDoctor(doctorId);
    }

    public Visit getNextVisit(Long patientId) throws VisitNotFound {
        return visitRepository.getNextVisit(patientId)
                .orElseThrow(() -> new VisitNotFound("No upcomming visits"));
    }

    public List<Integer> getMonthlyVisitCount(Long doctorId) {
        List<Integer> monthlyCount = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthlyCount.add(visitRepository.getMonthlyVisitCount(i, doctorId));
        }
        return monthlyCount;
    }

    public List<VisitAvailableDto> getAvailableHours(LocalDateTime startDate, LocalDateTime endDate) {
        List<Pair<Doctor, LocalDateTime>> occupiedHours = visitRepository.findAllByDate(startDate, endDate).stream().map(Visit::returnPair).collect(Collectors.toList());
        endDate = endDate.plusHours(17);
        startDate = startDate.plusHours(8);
        // 8-16 wizyta trwa 15 min
        List<Doctor> ids = doctorService.getAll();
        List<Pair<Doctor, LocalDateTime>> availableHours = new ArrayList<>();
        while(startDate.isBefore(endDate)) {
            if (startDate.getHour() >= 17) {
                startDate = startDate.plusDays(1).minusHours(9);
            }
            for (Doctor id : ids) {
                if (!(occupiedHours.contains((Pair.of(id, startDate))))) {
                    availableHours.add(Pair.of(id, startDate));
                }
            }
            startDate = startDate.plusMinutes(15);
        }
        return availableHours.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private VisitAvailableDto convertToDto(Pair<Doctor, LocalDateTime> e) {
        return new VisitAvailableDto(e.getFirst().getName(), e.getFirst().getSurname(), e.getSecond(), e.getFirst().getUserId());
    }
}
