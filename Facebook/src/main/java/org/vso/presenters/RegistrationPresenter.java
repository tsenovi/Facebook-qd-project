package org.vso.presenters;

import org.vso.constants.Participant;
import org.vso.constants.RegistrationStatus;
import org.vso.domain.AuthenticationService;
import org.vso.dto.UserRegistrationDTO;
import org.vso.utils.EmailValidator;
import org.vso.views.RegistrationView;

public class RegistrationPresenter {

    private final RegistrationView registrationView;
    private final AuthenticationService authenticationService;
    private final EmailValidator emailValidator;

    public RegistrationPresenter(RegistrationView registrationView) {
        this.registrationView = registrationView;
        this.authenticationService = new AuthenticationService();
        this.emailValidator = new EmailValidator();
    }

    public void onViewShown() {
        registrationView.showRegistrationInstructions();
        onInstructionsShown();
    }

    private void onInstructionsShown() {
        UserRegistrationDTO userRegistrationInfo = getUserInfo();
        RegistrationStatus registrationStatus = authenticationService.registerUser(userRegistrationInfo);
        if (registrationStatus == RegistrationStatus.REGISTRATION_SUCCESSFUL)
            registrationView.showRegistrationSuccess();
        else registrationView.showRegistrationError();
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
