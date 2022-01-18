package com.example.backend.visit;

import com.example.backend.doctor.Doctor;
import com.example.backend.patient.Patient;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "PAP_VISIT", schema = "Z14")
public class Visit {
    private Long id;
    private LocalDateTime date;
    private String description;
    private String location;
    private Doctor doctorByDoctorUserId;
    private Patient patientByPatientUserId;
    private Integer visitDuration;

    @Id
    @Column(name = "VISIT_ID", nullable = false)
    @SequenceGenerator(name = "VISIT", sequenceName = "VISIT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VISIT")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "VISIT_DATE", nullable = false)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Basic
    @Column(name = "DESCRIPTION", length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "LOCATION", nullable = false, length = 50)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(id, visit.id) && Objects.equals(date, visit.date) && Objects.equals(description, visit.description) && Objects.equals(location, visit.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, description, location);
    }

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "USER_ID", nullable = false)
    public Doctor getDoctorByDoctorUserId() {
        return doctorByDoctorUserId;
    }

    public void setDoctorByDoctorUserId(Doctor DoctorByDoctorUserId) {
        this.doctorByDoctorUserId = DoctorByDoctorUserId;
    }

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "USER_ID", nullable = false)
    public Patient getPatientByPatientUserId() {
        return patientByPatientUserId;
    }

    public void setPatientByPatientUserId(Patient PatientByPatientUserId) {
        this.patientByPatientUserId = PatientByPatientUserId;
    }

    public Pair<Doctor, LocalDateTime> returnPair() {
        return Pair.of(this.doctorByDoctorUserId, this.date);
    }

    @Basic
    @Column(name = "DURATION", scale = 4)
    public Integer getVisitDuration() {
        return visitDuration;
    }

    public void setVisitDuration(Integer visitDuration) {
        this.visitDuration = visitDuration;
    }
}
