package org.vso.presenters;

import org.vso.domain.AuthenticationService;
import org.vso.views.ProfileView;

public class ProfilePresenter {

    private final ProfileView profileView;

    private final AuthenticationService authenticationService;

    public ProfilePresenter(ProfileView profileView) {
        this.profileView = profileView;
        this.authenticationService = AuthenticationService.getInstance();
    }

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
