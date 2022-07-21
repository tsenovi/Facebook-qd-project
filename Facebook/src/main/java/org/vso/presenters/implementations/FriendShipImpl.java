package org.vso.presenters.implementations;

import org.vso.constants.FriendStatus;
import org.vso.models.dao.contracts.FriendShipDao;
import org.vso.models.dao.implementations.FriendShipDaoImpl;
import org.vso.models.data.FriendShip;
import org.vso.models.data.User;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.views.View;
import org.vso.views.contracts.FriendShipView;
import org.vso.views.implementations.FriendShipViewImpl;

public class FriendShipImpl implements BasePresenter {
    private final SearchPresenterImpl searchPresenter;
    private final FriendShipDao<FriendShip> friendShipDao;
    private final AuthenticationService authenticationService;
    private final View view;
    private final FriendShipView friendShipView;

    public FriendShipImpl() {
        this.searchPresenter = new SearchPresenterImpl();
        this.friendShipDao = new FriendShipDaoImpl();
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        this.view = new View();
        this.friendShipView = new FriendShipViewImpl();
    }

    public void sendFriendRequest(User sender, User requester) {
        FriendShip friendShip = new FriendShip();
        friendShip.setSender(sender);
        friendShip.setReceiver(requester);
        friendShip.setFriendStatus(FriendStatus.SENT);
        requester.getFriendShips().add(friendShip);
        friendShipDao.save(friendShip);
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

    private void declinedFriendRequest(FriendShip friendRequest) {
        authenticationService.getLoggedUser().getFriendShips().remove(friendRequest);
        FriendShip friendShip = new FriendShip(friendRequest.getSender(),
                friendRequest.getReceiver(),
                FriendStatus.DECLINED);
        friendShipDao.save(friendShip);
        friendShipView.showDeclined();
    }

    private void acceptFriendRequest(FriendShip friendRequest) {
        authenticationService.getLoggedUser().getFriends().add(friendRequest.getSender());
        friendRequest.getSender().getFriends().add(authenticationService.getLoggedUser());
        authenticationService.getLoggedUser().getFriendShips().remove(friendRequest);
        FriendShip friendShip = new FriendShip(friendRequest.getSender(),
                friendRequest.getReceiver(),
                FriendStatus.ACCEPTED);
        friendShipDao.save(friendShip);
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
