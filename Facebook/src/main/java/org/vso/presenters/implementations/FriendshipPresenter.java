package org.vso.presenters.implementations;

import org.vso.constants.FriendshipStatus;
import org.vso.models.data.Friendship;
import org.vso.models.data.User;
import org.vso.models.dto.FriendshipDTO;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.contracts.FriendshipService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.models.services.implementations.FriendshipServiceImpl;
import org.vso.views.implementations.searchViews.SearchViewImpl;
import org.vso.views.contracts.FriendshipView;
import org.vso.views.implementations.friendshipViews.FriendshipViewImpl;

public class FriendshipPresenter{
    private final SearchPresenter searchPresenter;
    private final FriendshipService friendshipService;
    private final AuthenticationService authenticationService;
    private final SearchViewImpl view;
    private final FriendshipView friendShipView;
    private final FriendshipDTO friendshipDTO;

    public FriendshipPresenter() {
        this.searchPresenter = new SearchPresenter();
        this.friendshipService = new FriendshipServiceImpl();
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        this.view = new SearchViewImpl();
        this.friendShipView = new FriendshipViewImpl();
        this.friendshipDTO = new FriendshipDTO();
    }

    public void createAFriendRequest(User sender, User requester) {
        Friendship friendShip = new Friendship();
        friendShip.setSender(sender);
        friendShip.setReceiver(requester);
        friendShip.setFriendshipStatus(FriendshipStatus.SENT);
        requester.getFriendShips().add(friendShip);
        friendshipService.save(friendShip);
    }

    public void showLoggedUserFriendRequest(){
        friendShipView.showAllFriendRequestsByUser(authenticationService.getLoggedUser());
    }

    public int chooseFriendRequest(){
        friendShipView.showChooseFriendRequest();
        int numberOfFriendRequest = view.getUserDecimalInput();
        return numberOfFriendRequest;
    }

    public Friendship getFriendship(){
        return authenticationService.getLoggedUser().getFriendShips().get(chooseFriendRequest());
    }

    public User getLoggedUser(){
        return authenticationService.getLoggedUser();
    }

    public void declinedFriendRequest(Friendship friendRequest) {
        friendshipDTO.removeFriendshipOfTheLoggedUser(friendRequest);
        authenticationService.getLoggedUser().getFriendShips().remove(friendRequest);
        Friendship friendship = new Friendship(friendRequest.getSender(),
                friendRequest.getReceiver(),
                FriendshipStatus.DECLINED);
        friendshipService.save(friendship);
        friendShipView.showDeclined();
    }

    public void acceptFriendRequest(Friendship friendRequest) {
        authenticationService.getLoggedUser().addFriend(friendRequest.getSender());
        friendRequest.getSender().addFriend(authenticationService.getLoggedUser());
        friendshipDTO.removeFriendshipOfTheLoggedUser(friendRequest);
        authenticationService.getLoggedUser().getFriendShips().remove(friendRequest);
        Friendship friendship = new Friendship(friendRequest.getSender(),
                friendRequest.getReceiver(),
                FriendshipStatus.ACCEPTED);
        friendshipService.save(friendship);
        friendShipView.showAccepted();
    }

    public void sentAFriendRequest(String firstName, String lastName) {
        User friend = searchPresenter.search(firstName, lastName);
        createAFriendRequest(authenticationService.getLoggedUser(), friend);
        friendShipView.showSent();
    }
}
