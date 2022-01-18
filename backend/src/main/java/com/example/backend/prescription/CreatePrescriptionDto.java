package com.example.backend.prescription;

import java.util.List;

public class CreatePrescriptionDto {
    private List<Long> medications;
    private Long amount;
    private Long doctorId;
    private Long patientId;

    public List<Long> getMedications() {
        return medications;
    }

    public void setMedications(List<Long> medications) {
        this.medications = medications;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }


}
