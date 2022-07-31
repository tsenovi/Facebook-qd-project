package org.vso.presenters.implementations;

import org.vso.constants.LoginStatus;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.models.dto.UserLoginDTO;
import org.vso.presenters.contracts.LoginPresenter;
import org.vso.views.contracts.LoginView;
import org.vso.views.contracts.ProfileView;
import org.vso.views.implementations.ProfileOptionsScreen;
import org.vso.views.implementations.ProfileViewImpl;

public class LoginPresenterImpl implements LoginPresenter {

    private final LoginView loginView;
    private ProfileView profileView;

    private final AuthenticationService authenticationService;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        this.profileView = null;
    }

    @Override
    public void onLoginButtonClicked() {
        loginView.getUserLoginInfo((email, password) -> {
            UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);
            onLoginInfoReceived(userLoginDTO);
        });
    }

    private void onLoginInfoReceived(UserLoginDTO userLoginDTO) {
        LoginStatus loginStatus = authenticationService.login(userLoginDTO);
        if (loginStatus == LoginStatus.LOGIN_SUCCESSFUL) {
            loginView.showLoginSuccessful();
            navigateToProfileOptionsScreen();
        } else {
            loginView.showLoginFailed();
        }
    }

    private void navigateToProfileOptionsScreen() {
        new ProfileOptionsScreen();
    }

    public interface UserLoginListener {
        void onUserLoginDataEntered(String email, String password);
    }
}
