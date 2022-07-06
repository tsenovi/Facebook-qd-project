package org.vso.models.dto;

public class UserLoginDTO {
    private final String email;
    private final String password;

    public UserLoginDTO(String userEmail, String userPassword) {
        this.email = userEmail;
        this.password = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
