package org.vso.presenters.implementations;

import org.vso.constants.FriendshipStatus;
import org.vso.models.data.Friendship;
import org.vso.models.data.User;
import org.vso.models.dto.FriendshipDTO;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.contracts.FriendshipService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.models.services.implementations.FriendshipServiceImpl;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.views.View;
import org.vso.views.contracts.FriendshipView;
import org.vso.views.implementations.FriendshipViewImpl;

public class FriendshipImpl implements BasePresenter {
    private final SearchPresenterImpl searchPresenter;
    private final FriendshipService friendshipService;
    private final AuthenticationService authenticationService;
    private final View view;
    private final FriendshipView friendShipView;
    private final FriendshipDTO friendshipDTO;

    public FriendshipImpl() {
        this.searchPresenter = new SearchPresenterImpl();
        this.friendshipService = new FriendshipServiceImpl();
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        this.view = new View();
        this.friendShipView = new FriendshipViewImpl();
        this.friendshipDTO = new FriendshipDTO();
    }

    public void sendFriendRequest(User sender, User requester) {
        Friendship friendShip = new Friendship();
        friendShip.setSender(sender);
        friendShip.setReceiver(requester);
        friendShip.setFriendshipStatus(FriendshipStatus.SENT);
        requester.getFriendShips().add(friendShip);
        friendshipService.save(friendShip);
    }

    public void friendRequestOptionsOfUser(){
        friendShipView.showAllFriendRequestsOfAUser();
        friendShipView.showChooseFriendRequest();
        int friendRequest = view.getUserDecimalInput();
        friendShipView.showOptions();
        int option = view.getUserDecimalInput();
        switch (option){
            case 1: acceptFriendRequest(authenticationService.getLoggedUser().getFriendShips().get(friendRequest));break;
            case 2: declinedFriendRequest(authenticationService.getLoggedUser().getFriendShips().get(friendRequest));break;
        }
    }

    private void declinedFriendRequest(Friendship friendRequest) {
        friendshipDTO.removeFriendShipOfTheLoggedUser(friendRequest);
        Friendship friendship = new Friendship(friendRequest.getSender(),
                friendRequest.getReceiver(),
                FriendshipStatus.DECLINED);
        friendshipService.save(friendship);
        friendShipView.showDeclined();
    }

    private void acceptFriendRequest(Friendship friendRequest) {
        authenticationService.getLoggedUser().addFriend(friendRequest.getSender());
        friendRequest.getSender().addFriend(authenticationService.getLoggedUser());
        friendshipDTO.removeFriendShipOfTheLoggedUser(friendRequest);
        Friendship friendship = new Friendship(friendRequest.getSender(),
                friendRequest.getReceiver(),
                FriendshipStatus.ACCEPTED);
        friendshipService.save(friendship);
        friendShipView.showAccepted();
    }


    @Override
    public void onViewShown() {
        view.show("Name:");
        String name = view.getUserTextInput();
        User friend = searchPresenter.search(name);
        sendFriendRequest(authenticationService.getLoggedUser(), friend);
        friendShipView.showSent();
    }
}
