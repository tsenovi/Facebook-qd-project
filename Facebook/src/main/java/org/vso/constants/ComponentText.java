package org.vso.constants;

public enum ComponentText {
    APP_TITLE("Facebook"),
    LOGIN("Login"),
    REGISTER("Register"),
    FORGOT_PASSWORD("Forgot password?"),
    RETURN("Return"),
    EMAIL("Email:"),
    PASSWORD("Password:"),
    CONFIRM_PASSWORD("Confirm password:"),
    FIRST_NAME("First name:"),
    LAST_NAME("Last name:"),
    AGE("Age:"),
    INVALID_EMAIL_INPUT("Invalid email input!"),
    PASSWORDS_MUST_MATCH("Passwords must match!"),
    AGE_REQUIREMENT("You must be over 14 to register!"),
    SELECT_FILE("Select File"),
    SAVE_FILE("Save File"),
    DOT("."),
    IMAGE("Image"),
    UPLOAD_PHOTO("Upload Photo"),
    YOUR_THOUGHTS("Your thoughts?");
    private final String text;

    ComponentText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
