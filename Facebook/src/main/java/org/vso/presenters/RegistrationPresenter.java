package org.vso.presenters;

import org.vso.constants.Participant;
import org.vso.domain.AuthenticationService;
import org.vso.dto.UserDTO;
import org.vso.utils.EmailValidator;
import org.vso.views.RegistrationView;

public class RegistrationPresenter {

    private final AuthenticationService authenticationService;
    private final RegistrationView registrationView;

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
        boolean isRegistered = authenticationService.registerUser(getUserInfo());
        if (isRegistered) registrationView.showRegistrationSuccess();
        else registrationView.showRegistrationError();
    }

    private UserDTO getUserInfo() {
        String userEmail = getUserEmail();
        String userPassword = getUserPassword();
        String userFirstName = getUserFirstName();
        String userLastName = getUserLastName();
        int userAge = getUserAge();

        return new UserDTO(userEmail, userPassword, userFirstName, userLastName, userAge);
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
