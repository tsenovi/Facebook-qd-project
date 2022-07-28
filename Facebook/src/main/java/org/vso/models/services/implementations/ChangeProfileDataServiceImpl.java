package org.vso.models.services.implementations;

import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.contracts.ChangeProfileDataService;
import org.vso.utils.contracts.EmailValidator;
import org.vso.utils.implementations.EmailValidatorImpl;

public class ChangeProfileDataServiceImpl implements ChangeProfileDataService {
    private final AuthenticationService authenticationService;
    private final EmailValidator emailValidator;

    public ChangeProfileDataServiceImpl() {
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        this.emailValidator = new EmailValidatorImpl();
    }

    @Override
    public void editEmail(String newEmail, String repeatEmail) {
        if (emailValidator.isValidEmail(newEmail) &&
                newEmail.equals(repeatEmail)){
            authenticationService.getLoggedUser().setEmail(newEmail);
        }
    }

    @Override
    public void editPassword(String newPassword, String repeatPassword) {
        if (newPassword.equals(repeatPassword)){
            authenticationService.getLoggedUser().setPassword(newPassword);
        }
    }
}
