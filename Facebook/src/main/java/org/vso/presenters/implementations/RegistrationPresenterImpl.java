package org.vso.presenters.implementations;

import org.vso.constants.Participant;
import org.vso.constants.RegistrationStatus;
import org.vso.domain.contracts.AuthenticationService;
import org.vso.domain.implementations.AuthenticationServiceImpl;
import org.vso.dto.UserRegistrationDTO;
import org.vso.presenters.contracts.BasePresenter;
import org.vso.utils.contracts.EmailValidator;
import org.vso.utils.implementations.EmailValidatorImpl;
import org.vso.views.contracts.ProfileView;
import org.vso.views.implementations.ProfileViewImpl;
import org.vso.views.contracts.RegistrationView;

public class RegistrationPresenterImpl implements BasePresenter {

    private final RegistrationView registrationView;

    private ProfileView profileView;

    private final AuthenticationService authenticationService;

    private final EmailValidator emailValidator;

    public RegistrationPresenterImpl(RegistrationView registrationView) {
        this.registrationView = registrationView;
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        this.emailValidator = new EmailValidatorImpl();
        this.profileView = null;
    }

    @Override
    public void onViewShown() {
        registrationView.showRegistrationInstructions();
        onInstructionsShown();
    }

    private void onInstructionsShown() {
        UserRegistrationDTO userRegistrationInfo = getUserInfo();
        RegistrationStatus registrationStatus = authenticationService.registerUser(userRegistrationInfo);
        if (registrationStatus == RegistrationStatus.REGISTRATION_SUCCESSFUL) {
            registrationView.showRegistrationSuccess();
            this.profileView = new ProfileViewImpl();
        } else {
            registrationView.showRegistrationError();
        }
    }

    private UserRegistrationDTO getUserInfo() {
        String userEmail = getUserEmail();
        String userPassword = getUserPassword();
        String userFirstName = getUserFirstName();
        String userLastName = getUserLastName();
        int userAge = getUserAge();

        return new UserRegistrationDTO(userEmail, userPassword, userFirstName, userLastName, userAge);
    }

    private int getUserAge() {
        int userAge;
        do {
            registrationView.askUserForAgeInput();
            userAge = registrationView.getUserDecimalInput();
        } while (userAge < Participant.MIN_AGE);

        return userAge;
    }

    private String getUserLastName() {
        registrationView.askUserForLastNameInput();

        return registrationView.getUserTextInput();
    }

    private String getUserFirstName() {
        registrationView.askUserForFirstNameInput();

        return registrationView.getUserTextInput();
    }

    private String getUserPassword() {
        String userPassword;
        String userRepeatedPassword;
        do {
            registrationView.askUserForPasswordInput();
            userPassword = registrationView.getUserTextInput();
            registrationView.askUserForPasswordRepeatInput();
            userRepeatedPassword = registrationView.getUserTextInput();
        } while (!userPassword.equals(userRepeatedPassword));

        return userPassword;
    }

    private String getUserEmail() {
        String userEmail;
        do {
            registrationView.askUserForEmailInput();
            userEmail = registrationView.getUserTextInput();
        } while (!emailValidator.isValidEmail(userEmail));

        return userEmail;
    }
}
