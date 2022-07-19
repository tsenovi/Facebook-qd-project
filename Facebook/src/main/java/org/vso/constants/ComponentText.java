package org.vso.constants;

public enum ComponentText {
    LOGIN("Login"),
    RETURN("return"),
    FORGOT_PASSWORD("Forgot password?"),
    PASSWORD("Password:"),
    EMAIL("Email:"),
    INVALID_EMAIL_INPUT("Invalid email input!"),
    REGISTER("Register"),
    APP_TITLE("Facebook");

    private final String text;

    ComponentText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
