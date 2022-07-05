package org.vso.presenters.implementations;

import org.vso.constants.LoginStatus;
import org.vso.domain.contracts.AuthenticationService;
import org.vso.domain.implementations.AuthenticationServiceImpl;
import org.vso.dto.UserLoginDTO;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.utils.contracts.EmailValidator;
import org.vso.utils.implementations.EmailValidatorImpl;
import org.vso.views.contracts.LoginView;
import org.vso.views.contracts.ProfileView;
import org.vso.views.contracts.RegistrationView;
import org.vso.views.implementations.ProfileViewImpl;
import org.vso.views.implementations.RegistrationViewImpl;

public class LoginPresenterImpl implements BasePresenter {

    private final LoginView loginView;

    private RegistrationView registrationView;

    private ProfileView profileView;

    private final AuthenticationService authenticationService;

    private final EmailValidator emailValidator;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        this.emailValidator = new EmailValidatorImpl();
        this.registrationView = null;
        this.profileView = null;
    }

    @Override
    public void onViewShown() {
        while (!authenticationService.hasLoggedUser()) {
            loginView.showUserInstructions();
            int userOption = loginView.getUserDecimalInput();
            switch (userOption) {
                case 1 -> runLoginProcess();
                case 2 -> runRegistrationProcess();
                case 3 -> runResetPasswordProcess();
                default -> loginView.showOptionError();
            }
        }
    }

    //TODO
    private void runResetPasswordProcess() {

    }

    private void runRegistrationProcess() {
        this.registrationView = new RegistrationViewImpl();
    }

    private void runLoginProcess() {
        loginView.showLoginInstructions();
        onLoginInstructionsShown();
    }

    private void onLoginInstructionsShown() {
        UserLoginDTO userLoginInfo = getUserLoginInfo();
        LoginStatus loginStatus = authenticationService.login(userLoginInfo);
        if (loginStatus == LoginStatus.LOGIN_SUCCESSFUL) {
            loginView.showLoginSuccessful();
            this.profileView = new ProfileViewImpl();
        } else {
            loginView.showLoginError();
        }
    }

    private UserLoginDTO getUserLoginInfo() {
        String userEmail = getUserEmail();
        String userPassword = getUserPassword();

        return new UserLoginDTO(userEmail, userPassword);
    }

    private String getUserPassword() {
        loginView.askUserForPasswordInput();

        return loginView.getUserTextInput();
    }

    private String getUserEmail() {
        loginView.askUserForEmailInput();
        String userEmail = loginView.getUserTextInput();
        while (!emailValidator.isValidEmail(userEmail)) {
            loginView.askUserForValidEmailInput();
            userEmail = loginView.getUserTextInput();
        }

        return userEmail;
    }
}
