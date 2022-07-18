package org.vso.presenters.implementations;

import org.vso.constants.FriendStatus;
import org.vso.models.dao.contracts.FriendShipDao;
import org.vso.models.dao.implementations.FriendShipDaoImpl;
import org.vso.models.data.FriendShip;
import org.vso.models.data.PublicUser;
import org.vso.models.data.User;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.views.View;

public class FriendRequestImpl implements BasePresenter {
    private final SearchPresenterImpl searchPresenter;
    private final FriendShipDao<FriendShip> friendShipDao;
    private final AuthenticationService authenticationService;
    private final View view;

    public FriendRequestImpl() {
        this.searchPresenter = new SearchPresenterImpl();
        this.friendShipDao = new FriendShipDaoImpl();
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        this.view = new View();
    }

    public void sendFriendRequest(PublicUser loggedUser, User friend){
        FriendShip friendShip = new FriendShip();
        friendShip.setUserId(loggedUser.getId());
        friendShip.setFriendId(friend.getId());
        friendShip.setFriendStatus(FriendStatus.SENT);
            friendShipDao.save(friendShip);
    }

    @Override
    public void onViewShown() {
        view.show("Name:");
        String name = view.getUserTextInput();
        sendFriendRequest(authenticationService.getLoggedUser(), searchPresenter.search(name));
        view.show("Sent");
    }
}
