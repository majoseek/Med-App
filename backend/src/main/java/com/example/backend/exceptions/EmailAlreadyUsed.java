package com.example.backend.exceptions;

public class EmailAlreadyUsed extends RuntimeException {
    public EmailAlreadyUsed(String message) {super(message);}
}
