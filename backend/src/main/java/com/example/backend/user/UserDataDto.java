package com.example.backend.user;

import org.jetbrains.annotations.NotNull;

public class UserDataDto {
    public static class UserData {
        private Long id;
        private String email;
        private String role;

        public UserData(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.role = user.getRole();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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
            super(user);
            this.name = user.getPatientById().getName();
            this.surname = user.getPatientById().getSurname();
            this.pesel = user.getPatientById().getPesel();
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
            super(user);
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
            super(user);
        }
    }
}
