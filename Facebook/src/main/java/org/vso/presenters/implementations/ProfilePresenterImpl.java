package org.vso.presenters.implementations;

import org.vso.models.domain.contracts.AuthenticationService;
import org.vso.models.domain.implementations.AuthenticationServiceImpl;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.views.contracts.ProfileView;

public class ProfilePresenterImpl implements BasePresenter {

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
