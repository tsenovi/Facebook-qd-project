package org.vso.views.contracts;

public interface RegistrationView {
    void show(String text);

    String getUserTextInput();

    Integer getUserDecimalInput();

    void showRegistrationInstructions();

    void showRegistrationSuccess();

    void showRegistrationError();

    void askUserForEmailInput();

    void askUserForPasswordInput();

    void askUserForPasswordRepeatInput();

    void askUserForFirstNameInput();

    void askUserForLastNameInput();

    void askUserForAgeInput();
}
