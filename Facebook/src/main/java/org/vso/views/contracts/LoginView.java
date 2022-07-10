package org.vso.views.contracts;

public interface LoginView {

    void showLoginInstructions();

    void askUserForEmailInput();

    void askUserForValidEmailInput();

    void askUserForPasswordInput();

    void showLoginSuccessful();

    void showLoginError();

    void showOptionError();

    void showUserInstructions();
}
