package com.example.backend.prescription;

import com.example.backend.medication.Medication;
import com.example.backend.medication.MedicationDto;

import java.util.List;

public class PrescriptionDto {
    private Long id;
    private List<Medication> medicationDto;
    private Long amount;
    private Long doctorId;
    private Long patientId;

    public PrescriptionDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Medication> getMedicationDto() {
        return medicationDto;
    }

    public void setMedicationDto(List<Medication> medicationDto) {
        this.medicationDto = medicationDto;
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
