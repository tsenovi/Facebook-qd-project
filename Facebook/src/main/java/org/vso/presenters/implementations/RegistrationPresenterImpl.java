package org.vso.presenters.implementations;

import org.vso.constants.Participant;
import org.vso.constants.RegistrationStatus;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.models.dto.UserRegistrationDTO;
import org.vso.presenters.contracts.RegistrationPresenter;
import org.vso.utils.contracts.EmailValidator;
import org.vso.utils.implementations.EmailValidatorImpl;
import org.vso.views.contracts.ProfileView;
import org.vso.views.implementations.ProfileViewImpl;
import org.vso.views.contracts.RegistrationView;

public class RegistrationPresenterImpl implements RegistrationPresenter {

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
    public void onRegistrationButtonClicked() {
        registrationView.getUserRegistrationInfo((email, password, confirmedPassword, firstName, lastName, age) -> {
            UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
                    email, password, confirmedPassword, firstName, lastName, age);
            onRegistrationInfoReceived(userRegistrationDTO);
        });
    }

    private void onRegistrationInfoReceived(UserRegistrationDTO userRegistrationDTO) {
        boolean isValidData = validateRegistrationData(userRegistrationDTO);
        if (isValidData) {
            RegistrationStatus registrationStatus = authenticationService.registerUser(userRegistrationDTO);
            if (registrationStatus == RegistrationStatus.REGISTRATION_SUCCESSFUL) {
                registrationView.showRegistrationSuccessful();
                navigateToProfilePage();
            } else {
                registrationView.showUserExistsMsg();
            }
        } else {
            registrationView.showRegistrationFailed();
        }
    }

    private void navigateToProfilePage() {
        registrationView.hideRegistrationPage();
        this.profileView = new ProfileViewImpl();
    }

    private boolean validateRegistrationData(UserRegistrationDTO userRegistrationDTO) {
        return userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmedPassword())
                && userRegistrationDTO.getAge() >= Participant.MIN_AGE
                && emailValidator.isValidEmail(userRegistrationDTO.getEmail());
    }

    public interface UserRegistrationListener {
        void onUserRegistrationDataEntered(
                String email,
                String password,
                String confirmedPassword,
                String firstName,
                String lastName,
                int age);
    }
}
