package org.vso.views;

import org.vso.presenters.LoginPresenter;

import java.util.Scanner;

public class LoginView {

    private final LoginPresenter loginPresenter;

    private final Scanner scanner;

    public LoginView() {
        this.loginPresenter = new LoginPresenter(this);
        this.scanner = new Scanner(System.in);
        loginPresenter.onViewShown();
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

    public void showLoginInstructions() {
        show("\tLogin Form");
    }

    public void askUserForEmailInput() {
        show("Email: ");
    }

    public void askUserForValidEmailInput() {
        show("Enter valid email: ");
    }

    public void askUserForPasswordInput() {
        show("Password: ");
    }

    public void showLoginSuccessful() {
        show("Login Successful!");
    }

    public void showLoginError() {
        show("Login Failed!");
    }

    public void showOptionError() {
        show("No such option!");
    }

    public void showUserInstructions() {
        show("""
                1. Login Form
                2. Registration Form
                3. Reset password Form""");
    }
}
