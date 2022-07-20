package org.vso.constants;

public enum RegistrationStatus {

    REGISTRATION_STATUS("Registration Status"),
    REGISTRATION_SUCCESSFUL("Registration Successful!"),
    REGISTRATION_FAILED("Registration Failed!"),
    EMAIL_EXISTS("Email already exists!");

    private final String text;

    RegistrationStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
