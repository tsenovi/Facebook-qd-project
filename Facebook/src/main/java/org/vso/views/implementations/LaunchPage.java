package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.views.implementations.authenticationViews.LoginViewImpl;
import org.vso.views.implementations.authenticationViews.RegistrationViewImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LaunchPage extends BaseFrame {
    private static LaunchPage instance;
    private final JButton loginButton;
    private final JButton registrationButton;

    private LaunchPage() {
        this.loginButton = new JButton();
        this.registrationButton = new JButton();

        setupComponents();
    }

    public static LaunchPage getInstance() {
        if (instance == null) instance = new LaunchPage();
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (loginButton.equals(source)) {
            navigateToLoginPage();
        } else if (registrationButton.equals(source)) {
            navigateToRegistrationPage();
        }
    }

    private void navigateToRegistrationPage() {
        this.dispose();
        RegistrationViewImpl.getInstance().setVisible(true);
    }

    private void navigateToLoginPage() {
        this.dispose();
        LoginViewImpl.getInstance().setVisible(true);
    }

    private void setupComponents() {
        setupLoginButton();
        setupRegistrationButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(loginButton);
        this.add(registrationButton);

        this.setTitle(ComponentText.APP_TITLE.getText());
        this.setVisible(true);
    }

    private void setupRegistrationButton() {
        registrationButton.setText(ComponentText.REGISTER.getText());
        registrationButton.setBounds(110, 220, 200, 40);
        registrationButton.setFocusable(false);
        registrationButton.addActionListener(this);
    }

    private void setupLoginButton() {
        loginButton.setText(ComponentText.LOGIN.getText());
        loginButton.setBounds(110, 100, 200, 40);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
    }
}
