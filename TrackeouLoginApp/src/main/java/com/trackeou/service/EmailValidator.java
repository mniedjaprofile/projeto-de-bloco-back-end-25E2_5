package com.trackeou.service;

import java.util.regex.Pattern;

public class EmailValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public boolean isValido(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}