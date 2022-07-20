package org.vso.views.contracts;

import org.vso.presenters.implementations.RegistrationPresenterImpl;

public interface RegistrationView {
    void getUserRegistrationInfo(RegistrationPresenterImpl.UserRegistrationListener listener);

    void showRegistrationSuccessful();

    void showRegistrationFailed();

    void showUserExistsMsg();

    void hideRegistrationPage();
}
