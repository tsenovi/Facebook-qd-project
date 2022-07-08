package org.vso.dto;

public class UserRegistrationDTO {

    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final Integer age;

    public UserRegistrationDTO(String email, String password, String firstName, String lastName, Integer age) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }
}
