package org.vso.views;

import org.vso.presenters.RegistrationPresenter;

import java.util.Scanner;

public class RegistrationView {

    private final RegistrationPresenter registrationPresenter;
    private final Scanner scanner;

    public RegistrationView() {
        this.registrationPresenter = new RegistrationPresenter(this);
        this.scanner = new Scanner(System.in);
        registrationPresenter.onViewShown();
    }

    public void show(String text) {
        System.out.println(text);
    }

    public String getUserTextInput() {
        return scanner.nextLine();
    }

    public Integer getUserDecimalInput() {
        return Integer.parseInt(getUserTextInput());
    }

    public void showRegistrationInstructions() {
        show("\tRegistration Form");
    }

    public void showRegistrationSuccess() {
        show("Registration Successful!");
    }

    public void showRegistrationError() {
        show("Registration Failed!");
    }

    public void askUserForEmailInput() {
        show("Email: ");
    }

    public void askUserForPasswordInput() {
        show("Password: ");
    }

    public void askUserForPasswordRepeatInput() {
        show("Repeat password: ");
    }

    public void askUserForFirstNameInput() {
        show("First name: ");
    }

    public void askUserForLastNameInput() {
        show("Last name: ");
    }

    public void askUserForAgeInput() {
        show("Users under 14 are not allowed to create account!");
        show("Age: ");
    }
}
