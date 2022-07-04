package org.vso.data;

public class PublicUser {

    private final long id;

    public PublicUser(User loggedUser) {
        this.id = loggedUser.getId();
    }

    public long getId() {
        return id;
    }
}
