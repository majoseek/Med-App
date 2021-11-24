package com.example.backend.doctor;

import com.example.backend.prescription.Prescription;
import com.example.backend.user.User;
import com.example.backend.visit.Visit;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PAP_DOCTOR", schema = "WKOLODZ2")
public class Doctor {
    private Long userId;
    private String name;
    private String surname;
    private String specialization;
    @JsonBackReference
    private User userByUserId;
    private Collection<Prescription> prescriptsByUserId;
    private Collection<Visit> visitsByUserId;

    @Id
    @Column(name = "PAP_USER_ID", nullable = false)
    @SequenceGenerator(name = "DOCTOR", sequenceName = "DOCTOR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCTOR")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long UserId) {
        this.userId = UserId;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SURNAME", nullable = false, length = 30)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "SPECIALIZATION", nullable = false, length = 20)
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(userId, doctor.userId) && Objects.equals(name, doctor.name) && Objects.equals(surname, doctor.surname) && Objects.equals(specialization, doctor.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, surname, specialization);
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @MapsId
    @JoinColumn(name = "PAP_USER_ID", referencedColumnName = "ID", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User UserByUserId) {
        this.userByUserId = UserByUserId;
    }

    @OneToMany(mappedBy = "doctorByDoctorUserId")
    public Collection<Prescription> getPrescriptsByUserId() {
        return prescriptsByUserId;
    }

    public void setPrescriptsByUserId(Collection<Prescription> PrescriptsByUserId) {
        this.prescriptsByUserId = PrescriptsByUserId;
    }

    @OneToMany(mappedBy = "doctorByDoctorUserId")
    public Collection<Visit> getVisitsByUserId() {
        return visitsByUserId;
    }

    public void setVisitsByUserId(Collection<Visit> VisitsByUserId) {
        this.visitsByUserId = VisitsByUserId;
    }
}
