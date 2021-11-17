package com.example.backend.illness;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class IllnessMapPK implements Serializable {
    private Long illnessId;
    private Long patientUserId;

    @Column(name = "PAP_ILLNESS_ID", nullable = false)
    @Id
    public Long getIllnessId() {
        return illnessId;
    }

    public void setIllnessId(Long IllnessId) {
        this.illnessId = IllnessId;
    }

    @Column(name = "PAP_PATIENT_PAP_USER_ID", nullable = false)
    @Id
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
        IllnessMapPK that = (IllnessMapPK) o;
        return Objects.equals(illnessId, that.illnessId) && Objects.equals(patientUserId, that.patientUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(illnessId, patientUserId);
    }
}
