package org.vso.models.data;

public class PublicUser {

    private final Integer id;

    public PublicUser(User loggedUser) {
        this.id = loggedUser.getId();
    }

    public int getId() {
        return id;
    }
}
