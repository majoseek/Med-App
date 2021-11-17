package com.example.backend.user;

import com.example.backend.doctor.Doctor;
import com.example.backend.patient.Patient;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PAP_USER", schema = "WKOLODZ2")
public class User {
    private Long id;
    private String email;
    private String password;
    private String role;
    private Doctor doctorById;
    private Patient patientById;

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "USER", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EMAIL", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "ROLE", nullable = false, length = 10)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, role);
    }

    @OneToOne(mappedBy = "userByUserId")
    public Doctor getDoctorById() {
        return doctorById;
    }

    public void setDoctorById(Doctor doctorById) {
        this.doctorById = doctorById;
    }

    @OneToOne(mappedBy = "userByUserId")
    public Patient getPatientById() {
        return patientById;
    }

    public void setPatientById(Patient patientById) {
        this.patientById = patientById;
    }
}
