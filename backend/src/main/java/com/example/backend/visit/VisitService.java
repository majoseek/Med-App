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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    public Visit createVisit(LocalDateTime date, String description, String location, Long doctorId, Long patientId, Integer duration) throws UserNotFound {
        Visit newVisit = new Visit();
        Doctor doctor = doctorService.getDoctorById(doctorId);
        Patient patient = patientService.getPatientById(patientId);
        newVisit.setDate(date);
        newVisit.setDescription(description);
        newVisit.setLocation(location);
        newVisit.setDoctorByDoctorUserId(doctor);
        newVisit.setPatientByPatientUserId(patient);
        newVisit.setVisitDuration(duration);
        visitRepository.save(newVisit);
        return newVisit;
    }

    public List<Visit> getUserVisits(Long userId) {
        return visitRepository.findAllByUserId(userId);
    }


    public Integer getVisitCountByMonth(Long doctorId) {
        return visitRepository.countVisitByMonthAndDoctor(doctorId);
    }

    public List<Visit> getNextVisits(Long patientId)  {
        return visitRepository.findNextVisits(patientId);
    }

    public List<Integer> getMonthlyVisitCount(Long doctorId) {
        List<Integer> monthlyCount = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthlyCount.add(visitRepository.getMonthlyVisitCount(i, doctorId));
        }
        return monthlyCount;
    }

    public Map<Doctor, List<LocalDateTime>> getAvailableHours(LocalDateTime startDate, LocalDateTime endDate, String spec) {
        List<Doctor> ids = doctorService.getAllDoctorsBySpecialization(spec);
        Map<Doctor, Map<LocalDateTime, Integer>> occupiedHours = visitRepository.findAllByDate(startDate, endDate, ids).stream()
                .collect(Collectors.groupingBy(Visit::getDoctorByDoctorUserId,
                        Collectors.toMap(Visit::getDate, Visit::getVisitDuration)));
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        List<LocalDate> dates = startDate.toLocalDate().datesUntil(endDate.toLocalDate()).filter(d-> !weekend.contains(d.getDayOfWeek())).collect(Collectors.toList());
        Map<Doctor, List<LocalDateTime>> availableDates = new HashMap<>();
        for (Doctor dr: ids) {
            List<LocalDateTime> availableHours = new ArrayList<>();
            dates.forEach(date -> {
                LocalDateTime start = date.atStartOfDay().plusHours(8);
                LocalDateTime end = date.atStartOfDay().plusHours(17);
                while (start.isBefore(end)) {
                    if (!occupiedHours.containsKey(dr)) {
                        availableHours.add(start);
                        start = start.plusMinutes(15);
                        continue;
                    }
                    Integer duration = occupiedHours.get(dr).getOrDefault(start, -1);
                    if (duration != -1) {
                        start = start.plusMinutes(duration);
                    } else {
                        availableHours.add(start);
                        start = start.plusMinutes(15);
                    }
                }
            });
            availableDates.put(dr, availableHours);
        }
        return availableDates;
    }

    public Visit getNextNextVisit(Long patientId) throws VisitNotFound {
        List<Visit> visits = visitRepository.getNextNextVisit(patientId);
        try {
            return visits.get(0);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new VisitNotFound("No upcoming visits");
        }
    }

    public List<Visit> getVisitHistory(Long patientId) { return visitRepository.getVisitHistory(patientId);}
}
