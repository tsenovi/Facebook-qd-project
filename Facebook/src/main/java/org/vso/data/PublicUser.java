package org.vso.data;

public class PublicUser {
    private final String firstName;
    private final String lastName;

    public PublicUser(User loggedUser) {
        this.firstName = loggedUser.getFirstName();
        this.lastName = loggedUser.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
