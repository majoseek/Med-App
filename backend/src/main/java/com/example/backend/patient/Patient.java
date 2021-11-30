package com.example.backend.patient;


import com.example.backend.illness.IllnessMap;
import com.example.backend.prescription.Prescription;
import com.example.backend.user.User;
import com.example.backend.visit.Visit;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PAP_PATIENT", schema = "WKOLODZ2")
public class Patient {
    private Long UserId;
    private String name;
    private String surname;
    private String pesel;
    private Collection<IllnessMap> illnessMapsByUserId;
    @JsonBackReference
    private User userByUserId;
    private Collection<Prescription> prescriptsByUserId;
    private Collection<Visit> visitsByUserId;

    @Id
    @Column(name = "PAP_USER_ID", nullable = false)
    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long UserId) {
        this.UserId = UserId;
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
    @Column(name = "PESEL", nullable = false, length = 11)
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient that = (Patient) o;
        return Objects.equals(UserId, that.UserId) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(pesel, that.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UserId, name, surname, pesel);
    }

    @OneToMany(mappedBy = "patientByPatientUserId")
    public Collection<IllnessMap> getIllnessMapsByUserId() {
        return illnessMapsByUserId;
    }

    public void setIllnessMapsByUserId(Collection<IllnessMap> IllnessMapsByUserId) {
        this.illnessMapsByUserId = IllnessMapsByUserId;
    }

    @OneToOne
    @MapsId
    @JoinColumn(name = "PAP_USER_ID", referencedColumnName = "ID", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User UserByUserId) {
        this.userByUserId = UserByUserId;
    }

    @OneToMany(mappedBy = "patientByPatientUserId")
    public Collection<Prescription> getPrescriptionByUserId() {
        return prescriptsByUserId;
    }

    public void setPrescriptionByUserId(Collection<Prescription> PrescriptsByUserId) {
        this.prescriptsByUserId = PrescriptsByUserId;
    }

    @OneToMany(mappedBy = "patientByPatientUserId")
    public Collection<Visit> getVisitsByUserId() {
        return visitsByUserId;
    }

    public void setVisitsByUserId(Collection<Visit> VisitsByUserId) {
        this.visitsByUserId = VisitsByUserId;
    }
}
