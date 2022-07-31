package org.vso.views.contracts;

import org.vso.models.data.User;

public interface FriendshipView {

    void showAllFriendRequestsByUser(User user);

    void showChooseFriendRequest();

    void showSent();

    void showOptions();

    void showAccepted();

    void showDeclined();

}
