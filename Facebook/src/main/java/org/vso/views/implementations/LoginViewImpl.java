package org.vso.views.implementations;

import org.vso.presenters.contracts.BasePresenter;
import org.vso.presenters.implementations.LoginPresenterImpl;
import org.vso.views.contracts.LoginView;

import java.util.Scanner;

public class LoginViewImpl implements LoginView {

    private final BasePresenter loginPresenter;

    private final Scanner scanner;

    public LoginViewImpl() {
        this.loginPresenter = new LoginPresenterImpl(this);
        this.scanner = new Scanner(System.in);
        loginPresenter.onViewShown();
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
    public void showLoginInstructions() {
        show("\tLogin Form");
    }

    @Override
    public void askUserForEmailInput() {
        show("Email: ");
    }

    @Override
    public void askUserForValidEmailInput() {
        show("Enter valid email: ");
    }

    @Override
    public void askUserForPasswordInput() {
        show("Password: ");
    }

    @Override
    public void showLoginSuccessful() {
        show("Login Successful!");
    }

    @Override
    public void showLoginError() {
        show("Login Failed!");
    }

    @Override
    public void showOptionError() {
        show("No such option!");
    }

    @Override
    public void showUserInstructions() {
        show("""
                \tFacebook
                1. Login Form
                2. Registration Form
                3. Reset password Form""");
    }
}
