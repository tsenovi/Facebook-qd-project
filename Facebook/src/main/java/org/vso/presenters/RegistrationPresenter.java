package org.vso.presenters;

import org.vso.constants.Participant;
import org.vso.domain.RegistrationService;
import org.vso.dto.UserDTO;
import org.vso.views.RegistrationView;

public class RegistrationPresenter {

    private final RegistrationService registrationService;
    private final RegistrationView registrationView;

    public RegistrationPresenter(RegistrationView registrationView) {
        this.registrationView = registrationView;
        this.registrationService = new RegistrationService();
    }

    public void onViewShown() {
        registrationView.showRegistrationInstructions();
        onInstructionsShown();
    }

    private void onInstructionsShown() {
        boolean isRegistered = registrationService.onUserInfoEntered(getUserInfo());
        if(isRegistered){
            registrationView.showRegistrationSuccess();
        }
        else registrationView.showRegistrationError();
    }

    private UserDTO getUserInfo() {
        String userEmail = registrationView.getUserTextInput("Email: ");
        String userPassword;
        String userRepeatedPassword;
        do {
            userPassword = registrationView.getUserTextInput("Password: ");
            userRepeatedPassword = registrationView.getUserTextInput("Repeat password: ");
        } while (!userPassword.equals(userRepeatedPassword));

        String userFirstName = registrationView.getUserTextInput("First name: ");
        String userLastName = registrationView.getUserTextInput("Last name: ");

        int userAge;
        do {
            userAge = registrationView.getUserDecimalInput("Age: ");
        } while (userAge < Participant.MIN_AGE);

        return new UserDTO(userEmail, userPassword, userFirstName, userLastName, userAge);
    }
}
