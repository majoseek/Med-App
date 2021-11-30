package com.example.backend.prescription;

import com.example.backend.doctor.Doctor;
import com.example.backend.patient.Patient;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PAP_PRESCRIPT", schema = "Z14")
public class Prescription {
    private Long id;
    private String medicationName;
    private Long amount;
    private Doctor doctorByDoctorUserId;
    private Patient patientByPatientUserId;

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "PRESCRIPTION", sequenceName = "PRESCRIPT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRESCRIPTION")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MEDICATION_NAME", nullable = false, length = 20)
    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    @Basic
    @Column(name = "AMOUNT", nullable = false)
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(id, that.id) && Objects.equals(medicationName, that.medicationName) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicationName, amount);
    }

    @ManyToOne
    @JoinColumn(name = "PAP_DOCTOR_PAP_USER_ID", referencedColumnName = "PAP_USER_ID", nullable = false)
    public Doctor getDoctorByDoctorUserId() {
        return doctorByDoctorUserId;
    }

    public void setDoctorByDoctorUserId(Doctor DoctorByDoctorUserId) {
        this.doctorByDoctorUserId = DoctorByDoctorUserId;
    }

    @ManyToOne
    @JoinColumn(name = "PAP_PATIENT_PAP_USER_ID", referencedColumnName = "PAP_USER_ID", nullable = false)
    public Patient getPatientByPatientUserId() {
        return patientByPatientUserId;
    }

    public void setPatientByPatientUserId(Patient PatientByPatientUserId) {
        this.patientByPatientUserId = PatientByPatientUserId;
    }
}
