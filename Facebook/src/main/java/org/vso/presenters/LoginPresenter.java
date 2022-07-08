package org.vso.presenters;

import org.vso.constants.LoginStatus;
import org.vso.domain.AuthenticationService;
import org.vso.dto.UserLoginDTO;
import org.vso.utils.EmailValidator;
import org.vso.views.LoginView;
import org.vso.views.RegistrationView;
import org.vso.views.View;

public class LoginPresenter {

    private final LoginView loginView;

    private View view;

    private RegistrationView registrationView;

    private final AuthenticationService authenticationService;

    private final EmailValidator emailValidator;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        this.view = new View();
        this.authenticationService = new AuthenticationService();
        this.emailValidator = new EmailValidator();
        this.registrationView = null;
    }

    public void onViewShown() {
        while (true) {
            loginView.showUserInstructions();
            int userOption = view.getUserDecimalInput();
            switch (userOption) {
                case 1 : runLoginProcess();break;
                case 2 : runRegistrationProcess();break;
                case 3 : runResetPasswordProcess();break;
                default : loginView.showOptionError();break;
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
        return view.getUserTextInput();
    }

    private String getUserEmail() {
        loginView.askUserForEmailInput();
        String userEmail = view.getUserTextInput();
        while (!emailValidator.isValidEmail(userEmail)) {
            loginView.askUserForValidEmailInput();
            userEmail = view.getUserTextInput();
        }

        return userEmail;
    }
}
