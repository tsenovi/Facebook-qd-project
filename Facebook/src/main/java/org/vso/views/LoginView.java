package org.vso.views;

import org.vso.presenters.LoginPresenter;

import java.util.Scanner;

public class LoginView {

    private final LoginPresenter loginPresenter;
    private View view;

    public LoginView() {
        this.view = new View();
        this.loginPresenter = new LoginPresenter(this);
        loginPresenter.onViewShown();
    }

    public void showLoginInstructions() {
        view.show("\tLogin Form");
    }

    public void askUserForEmailInput() {
        view.show("Email: ");
    }

    public void askUserForValidEmailInput() {
        view.show("Enter valid email: ");
    }

    public void askUserForPasswordInput() {
        view.show("Password: ");
    }

    public void showLoginSuccessful() {
        view.show("Login Successful!");
    }

    public void showLoginError() {
        view.show("Login Failed!");
    }

    public void showOptionError() {
        view.show("No such option!");
    }

    public void showUserInstructions() {
        view.show("1. Login Form\n" +
                "2. Registration Form\n" +
                "3. Reset password Form");
    }
}
