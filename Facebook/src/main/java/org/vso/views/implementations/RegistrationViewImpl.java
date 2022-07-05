package org.vso.views.implementations;

import org.vso.presenters.contracts.BasePresenter;
import org.vso.presenters.implementations.RegistrationPresenterImpl;
import org.vso.views.contracts.RegistrationView;

import java.util.Scanner;

public class RegistrationViewImpl implements RegistrationView {

    private final BasePresenter registrationPresenter;

    private final Scanner scanner;

    public RegistrationViewImpl() {
        this.registrationPresenter = new RegistrationPresenterImpl(this);
        this.scanner = new Scanner(System.in);
        registrationPresenter.onViewShown();
    }

    @Override
    public void show(String text) {
        System.out.println(text);
    }

    @Override
    public String getUserTextInput() {
        return scanner.nextLine();
    }

    @Override
    public Integer getUserDecimalInput() {
        return Integer.parseInt(getUserTextInput());
    }

    @Override
    public void showRegistrationInstructions() {
        show("\tRegistration Form");
    }

    @Override
    public void showRegistrationSuccess() {
        show("Registration Successful!");
    }

    @Override
    public void showRegistrationError() {
        show("Registration Failed!");
    }

    @Override
    public void askUserForEmailInput() {
        show("Email: ");
    }

    @Override
    public void askUserForPasswordInput() {
        show("Password: ");
    }

    @Override
    public void askUserForPasswordRepeatInput() {
        show("Repeat password: ");
    }

    @Override
    public void askUserForFirstNameInput() {
        show("First name: ");
    }

    @Override
    public void askUserForLastNameInput() {
        show("Last name: ");
    }

    @Override
    public void askUserForAgeInput() {
        show("Users under 14 are not allowed to create account!");
        show("Age: ");
    }
}
