package org.vso.presenters.implementations;

import org.vso.constants.FriendStatus;
import org.vso.models.dao.contracts.UserDao;
import org.vso.models.dao.implementations.UserDaoImpl;
import org.vso.models.data.FriendRequest;
import org.vso.models.data.User;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.views.View;

public class FriendRequestImpl implements BasePresenter {
    private final SearchPresenterImpl searchPresenter;
    private final UserDao<User> allUsers;
    private final View view;

    public FriendRequestImpl() {
        this.searchPresenter = new SearchPresenterImpl();
        this.allUsers = new UserDaoImpl();
        this.view = new View();
    }

    public void sendFriendRequest(User friend){
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setId((long)1);
        friendRequest.setFriendId(friend.getId());
        friendRequest.setFriendStatus(FriendStatus.SENT);
            allUsers.save(friendRequest);
    }

    @Override
    public void onViewShown() {
        view.show("Name:");
        String name = view.getUserTextInput();
        sendFriendRequest(searchPresenter.search(name));
    }
}
