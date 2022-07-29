package org.vso.models.data;

public class PublicUser {

    private final Long id;

    public PublicUser(User loggedUser) {
        this.id = loggedUser.getId();
    }

    public Long getId() {
        return id;
    }
}
