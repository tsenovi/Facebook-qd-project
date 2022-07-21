package org.vso.views.implementations;

import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.views.View;
import org.vso.views.contracts.FriendShipView;

public class FriendShipViewImpl implements FriendShipView {
    private final AuthenticationService authenticationService;
    private final View view;

    public FriendShipViewImpl() {
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        this.view = new View();
    }

    @Override
    public void showAllFriendRequestsOfAUser() {
        for (int i = 0; i < authenticationService.getLoggedUser().getFriendShips().size();i++) {
            System.out.print(i);
            System.out.print(". ");
            view.showTextWithoutNextLine(authenticationService.getLoggedUser().getFriendShips().get(i).getSender().getFirstName());
            view.showTextWithoutNextLine(authenticationService.getLoggedUser().getFriendShips().get(i).getSender().getLastName());
            view.show(" send you a friend request");
        }
    }

    @Override
    public void showChooseFriendRequest() {
        view.show("Choose friend request:");
    }

    @Override
    public void showSent() {
        view.show("Sent");
    }

    @Override
    public void showOptions() {
        view.show("1. Accept\n" +
                "2. Declined");
    }

    @Override
    public void showAccepted() {
        view.show("Accepted");
    }

    @Override
    public void showDeclined() {
        view.show("Declined");
    }
}
