package org.vso.data;

public class PublicUser {

    private final String email;
    private final String firstName;
    private final String lastName;
    private final int age;


    public PublicUser(User loggedUser) {
        this.email = loggedUser.getEmail();
        this.firstName = loggedUser.getFirstName();
        this.lastName = loggedUser.getLastName();
        this.age = loggedUser.getAge();
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
