package org.vso.views.implementations.friendshipViews;

import org.vso.models.data.User;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.views.contracts.FriendshipView;
import org.vso.views.implementations.searchViews.SearchViewImpl;

public class FriendshipViewImpl implements FriendshipView {

    public FriendshipViewImpl() {

    }

    @Override
    public void showAllFriendRequestsByUser(User user) {
        for (int i = 0; i < user.getFriendShips().size();i++) {
            showNumber(i);
            showTextWithoutNextLine(". ");
            showTextWithoutNextLine(user.getFriendShips().get(i).getSender().getFirstName());
            showTextWithoutNextLine(user.getFriendShips().get(i).getSender().getLastName());
            show(" send you a friend request");
        }
    }

    private void showNumber(int number){
        System.out.print(number);
    }

    private void show (String text){
        System.out.println(text);
    }

    private void showTextWithoutNextLine(String text){
        System.out.print(text);
    }

    @Override
    public void showChooseFriendRequest() {
        show("Choose friend request:");
    }

    @Override
    public void showSent() {
        show("Sent");
    }

    @Override
    public void showOptions() {
        show("1. Accept\n" +
                "2. Declined");
    }

    @Override
    public void showAccepted() {
        show("Accepted");
    }

    @Override
    public void showDeclined() {
        show("Declined");
    }
}
