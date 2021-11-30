package com.example.backend.illness;

import com.example.backend.patient.Patient;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PAP_ILLNESS_MAP", schema = "Z14")
@IdClass(IllnessMapPK.class)
public class IllnessMap {
    private Long illnessId;
    private Long patientUserId;
    private Illness illnessByIllnessId;
    private Patient patientByPatientUserId;

    @Id
    @Column(name = "PAP_ILLNESS_ID", nullable = false)
    public Long getIllnessId() {
        return illnessId;
    }

    public void setIllnessId(Long IllnessId) {
        this.illnessId = IllnessId;
    }

    @Id
    @Column(name = "PAP_PATIENT_PAP_USER_ID", nullable = false)
    public Long getPatientUserId() {
        return patientUserId;
    }

    public void setPatientUserId(Long PatientUserId) {
        this.patientUserId = PatientUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IllnessMap that = (IllnessMap) o;
        return Objects.equals(illnessId, that.illnessId) && Objects.equals(patientUserId, that.patientUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(illnessId, patientUserId);
    }

    @ManyToOne
    @JoinColumn(name = "PAP_ILLNESS_ID", referencedColumnName = "ID", nullable = false, insertable=false, updatable = false)
    public Illness getIllnessByIllnessId() {
        return illnessByIllnessId;
    }

    public void setIllnessByIllnessId(Illness IllnessByIllnessId) {
        this.illnessByIllnessId = IllnessByIllnessId;
    }

    @ManyToOne
    @JoinColumn(name = "PAP_PATIENT_PAP_USER_ID", referencedColumnName = "PAP_USER_ID", nullable = false, insertable=false, updatable = false)
    public Patient getPatientByPatientUserId() {
        return patientByPatientUserId;
    }

    public void setPatientByPatientUserId(Patient PatientByPatientUserId) {
        this.patientByPatientUserId = PatientByPatientUserId;
    }
}
