package com.example.backend.prescription;

import java.util.List;

public class CreateByPeselPrescriptionDto {
    private String pesel;
    private List<Long> medications;
    private Long amount;
    private Long doctorId;

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public List<Long> getMedications() {
        return medications;
    }

    public void setMedications(List<Long> mediations) {
        this.medications = mediations;
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
}
