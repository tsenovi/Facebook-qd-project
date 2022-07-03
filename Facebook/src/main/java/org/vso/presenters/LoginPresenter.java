package org.vso.presenters;

import org.vso.constants.LoginStatus;
import org.vso.constants.RegistrationStatus;
import org.vso.domain.AuthenticationService;
import org.vso.dto.UserLoginDTO;
import org.vso.utils.EmailValidator;
import org.vso.views.LoginView;
import org.vso.views.RegistrationView;

public class LoginPresenter {

    private final LoginView loginView;

    private RegistrationView registrationView;

    private final AuthenticationService authenticationService;

    private final EmailValidator emailValidator;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        this.authenticationService = new AuthenticationService();
        this.emailValidator = new EmailValidator();
        this.registrationView = null;
    }

    public void onViewShown() {
        while (true) {
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
        this.registrationView = new RegistrationView();
    }

    private void runLoginProcess() {
        loginView.showLoginInstructions();
        onInstructionsShown();
    }

    private void onInstructionsShown() {
        UserLoginDTO userLoginInfo = getUserLoginInfo();
        LoginStatus loginStatus = authenticationService.login(userLoginInfo);
        if (loginStatus == LoginStatus.LOGIN_SUCCESSFUL) loginView.showLoginSuccessful();
        else loginView.showLoginError();
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
