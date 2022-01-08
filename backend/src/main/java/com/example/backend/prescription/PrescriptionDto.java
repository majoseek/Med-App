package com.example.backend.prescription;

import com.example.backend.medication.Medication;
import com.example.backend.medication.MedicationDto;

import java.util.List;

public class PrescriptionDto {
    private Long id;
    private List<Medication> medicationDto;
    private Long amount;
    private String patientName;
    private String patientSurname;
    private String doctorName;
    private String doctorSurname;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

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

}
