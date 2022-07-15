package org.vso.presenters.implementations;

import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.views.contracts.ProfileView;

public class ProfilePresenterImpl implements BasePresenter {

    private final ProfileView profileView;
    private final FriendRequestImpl friendRequest;
    private final AuthenticationService authenticationService;

    public ProfilePresenterImpl(ProfileView profileView) {
        this.profileView = profileView;
        this.friendRequest = new FriendRequestImpl();
        this.authenticationService = AuthenticationServiceImpl.getInstance();
    }

    @Override
    public void onViewShown() {
        while (authenticationService.hasLoggedUser()) {
            profileView.showProfileOptions();
            int userOption = profileView.getUserDecimalInput();
            switch (userOption) {
                case 1 : runLogoutProcess();break;
                case 2 : friendRequest.search();break;
                case 3 : friendRequest.sendFriendRequest();break;
                default : profileView.showOptionError();break;
            }
        }
    }

    private void runLogoutProcess() {
        profileView.onUserLogoutSelected();
        authenticationService.onUserLogoutSelected();
    }
}
