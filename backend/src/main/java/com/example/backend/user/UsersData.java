package com.example.backend.user;

import com.example.backend.exceptions.InvalidRole;
import com.example.backend.patient.Patient;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UsersData {
    abstract static class UserData {
        private String email;
        private String password;
        private String role;

        public UserData(String email, String role) {
            this.email = email;
            this.role = role;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    public static final class PatientData extends UserData {
        private String name;
        private String surname;
        private String pesel;

        public PatientData(@NotNull User user){
            super(user.getEmail(), user.getRole());
            this.name = user.getPatientById().getName();
            this.surname = user.getPatientById().getSurname();
            this.pesel = user.getPatientById().getSurname();
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

        public String getPesel() {
            return pesel;
        }

        public void setPesel(String pesel) {
            this.pesel = pesel;
        }
    }

    public static final class DoctorData extends UserData {

        private String name;
        private String surname;
        private String specialization;

        public DoctorData(@NotNull User user) {
            super(user.getEmail(), user.getRole());
            this.name = user.getDoctorById().getName();
            this.surname = user.getDoctorById().getSurname();
            this.specialization = user.getDoctorById().getSpecialization();
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

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }

    }

    public static final class AdminData extends UserData {
        public AdminData(@NotNull User user) {
            super(user.getEmail(), user.getRole());
        }
    }
}
