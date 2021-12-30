package com.example.backend.user;

public enum UserRole {
    DOCTOR("doctor-user"), PATIENT("patient-user"), ADMIN("admin");

    public final String roleStr;

    UserRole(String role) {
        this.roleStr = role;
    }
}
