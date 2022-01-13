package com.example.backend.visit;

import java.time.LocalDateTime;

public class VisitAvailableDto {
    private String doctorName;
    private String doctorSurname;
    private LocalDateTime date;
    private Long id;

    VisitAvailableDto(String doctorName, String doctorSurname, LocalDateTime date, Long id) {
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.date = date;
        this.id = id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
