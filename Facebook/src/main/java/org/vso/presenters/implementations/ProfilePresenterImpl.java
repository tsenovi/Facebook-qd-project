package org.vso.presenters.implementations;

import org.vso.domain.contracts.AuthenticationService;
import org.vso.domain.implementations.AuthenticationServiceImpl;
import org.vso.presenters.contracts.ProfilePresenter;
import org.vso.views.contracts.ProfileView;

public class ProfilePresenterImpl implements ProfilePresenter {

    private final ProfileView profileView;

    private final AuthenticationService authenticationService;

    public ProfilePresenterImpl(ProfileView profileView) {
        this.profileView = profileView;
        this.authenticationService = AuthenticationServiceImpl.getInstance();
    }

    @Override
    public void onViewShown() {
        while (authenticationService.hasLoggedUser()) {
            profileView.showProfileOptions();
            int userOption = profileView.getUserDecimalInput();
            switch (userOption) {
                case 1 -> runLogoutProcess();
                default -> profileView.showOptionError();
            }
        }
    }

    private void runLogoutProcess() {
        profileView.onUserLogoutSelected();
        authenticationService.onUserLogoutSelected();
    }
}
