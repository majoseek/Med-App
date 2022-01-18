package com.example.backend.visit;

import java.time.LocalDateTime;
import java.util.List;

public class VisitAvailableDto {
    private Long id;
    private String doctorName;
    private String doctorSurname;
    private List<LocalDateTime> dates;


    VisitAvailableDto(String doctorName, String doctorSurname, List<LocalDateTime> dates, Long id) {
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.dates = dates;
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

    public List<LocalDateTime> getDates() {
        return dates;
    }

    public void setDates(List<LocalDateTime> dates) {
        this.dates = dates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
