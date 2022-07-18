package org.vso.constants;

public enum LoginStatus {
    LOGIN_STATUS("Login Status"),
    LOGIN_SUCCESSFUL("Login Successful!"),
    LOGIN_FAILED("Login Failed!");

    private final String text;

    LoginStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
