package org.vso.dto;

public class UserDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;

    public UserDTO(String email, String password, String firstName, String lastName, Integer age) {
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
