package com.example.backend.prescription;

import java.util.List;

public class CreatePrescriptionDto {
    private List<Long> medications;
    private Long amount;
    private String name;
    private String surname;

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
