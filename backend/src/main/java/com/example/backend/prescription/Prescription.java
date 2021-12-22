package com.example.backend.prescription;

import com.example.backend.doctor.Doctor;
import com.example.backend.medication.Medication;
import com.example.backend.patient.Patient;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PAP_PRESCRIPT", schema = "Z14")
public class Prescription {
    private Long id;
    private Long amount;
    private Doctor doctorByDoctorUserId;
    private Patient patientByPatientUserId;
    private Collection<Medication> medicationsByPrescriptId;

    @Id
    @Column(name = "PRESCRIPT_ID", nullable = false)
    @SequenceGenerator(name = "PRESCRIPTION", sequenceName = "PRESCRIPT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRESCRIPTION")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @ManyToMany( cascade = {CascadeType.ALL})
    @JoinTable(
            name = "PAP_MEDICSMAP",
            joinColumns = { @JoinColumn(name = "PRESCRIPT_ID")},
            inverseJoinColumns = { @JoinColumn(name = "MEDICATION_ID") }
    )
    public Collection<Medication> getMedicationsByPrescriptId() {
        return medicationsByPrescriptId;
    }

    public void setMedicationsByPrescriptId(Collection<Medication> medicationsByPrescriptId) {
        this.medicationsByPrescriptId = medicationsByPrescriptId;
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
}
