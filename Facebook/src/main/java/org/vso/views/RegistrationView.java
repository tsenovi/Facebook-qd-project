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

    public String getUserTextInput(String text) {
        show(text);
        return scanner.nextLine();
    }

    public Integer getUserDecimalInput(String text) {
        show(text);
        return scanner.nextInt();
    }

    public void showRegistrationInstructions() {
        show("""
                To register, please enter your information below:""");
    }

    public void showRegistrationSuccess() {
        show("Registration Successful");
    }

    public void showRegistrationError() {
        show("Registration Failure");
    }

    public void show(String text) {
        System.out.println(text);
    }
}
