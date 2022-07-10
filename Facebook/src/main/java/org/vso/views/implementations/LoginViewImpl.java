package org.vso.views.implementations;

import org.vso.presenters.contracts.BasePresenter;
import org.vso.presenters.implementations.LoginPresenterImpl;
import org.vso.views.View;
import org.vso.views.contracts.LoginView;


public class LoginViewImpl implements LoginView {
    private final BasePresenter loginPresenter;
    private final View view;


    public LoginViewImpl() {
        this.loginPresenter = new LoginPresenterImpl(this);
        this.view = new View();
        loginPresenter.onViewShown();
    }

    public void showLoginInstructions() {
        view.show("\tLogin Form");
    }

    @Override
    public void askUserForEmailInput() {
        view.show("Email: ");
    }

    @Override
    public void askUserForValidEmailInput() {
        view.show("Enter valid email: ");
    }

    @Override
    public void askUserForPasswordInput() {
        view.show("Password: ");
    }

    @Override
    public void showLoginSuccessful() {
        view.show("Login Successful!");
    }

    @Override
    public void showLoginError() {
        view.show("Login Failed!");
    }

    @Override
    public void showOptionError() {
        view.show("No such option!");
    }

    @Override
    public void showUserInstructions() {
        view.show("1. Login Form\n" +
                "2. Registration Form\n" +
                "3. Reset password Form");

    }
}
