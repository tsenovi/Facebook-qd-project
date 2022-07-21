package org.vso.presenters.implementations;

import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.views.contracts.ProfileView;
import org.vso.views.implementations.LoginViewImpl;

public class ProfilePresenterImpl implements BasePresenter {

    private final ProfileView profileView;
    private final FriendShipImpl friendRequest;
    private final SearchPresenterImpl searchPresenter;
    private final AuthenticationService authenticationService;

    public ProfilePresenterImpl(ProfileView profileView) {
        this.profileView = profileView;
        this.friendRequest = new FriendShipImpl();
        this.searchPresenter = new SearchPresenterImpl();
        this.authenticationService = AuthenticationServiceImpl.getInstance();
    }

    @Override
    public void onViewShown() {
        while (authenticationService.hasLoggedUser()) {
            profileView.showProfileOptions();
            int userOption = profileView.getUserDecimalInput();
            switch (userOption) {
                case 1 : runLogoutProcess();break;
                case 2 : searchPresenter.onViewShown();break;
                case 3 : friendRequest.onViewShown();break;
                case 4 : friendRequest.friendRequestOptionsOfUser();break;
                default : profileView.showOptionError();break;
            }
        }
    }

    private void runLogoutProcess() {
        profileView.onUserLogoutSelected();
        authenticationService.onUserLogoutSelected();
        navigateToLoginPage();
    }

    private void navigateToLoginPage() {
        LoginViewImpl.getInstance().showLoginPage();
    }
}
