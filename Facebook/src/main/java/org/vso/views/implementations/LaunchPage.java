package org.vso.views.implementations;

import org.vso.constants.ImagePathHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage extends JFrame implements ActionListener {
    private static LaunchPage instance;
    private final JButton loginButton;
    private final JButton registrationButton;
    private final JButton resetPasswordButton;

    private LaunchPage() {
        this.loginButton = new JButton();
        this.registrationButton = new JButton();
        this.resetPasswordButton = new JButton();

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
            //TODO
            //navigateToLoginPage();
        } else if (registrationButton.equals(source)) {
            //TODO
            //navigateToRegistrationPage();
        }
    }

    private void navigateToRegistrationPage() {
        this.dispose();
        new RegistrationViewImpl();
    }

    private void navigateToLoginPage() {
        this.dispose();
        new LoginViewImpl();
    }

    private void setupComponents() {
        setupLoginButton();
        setupRegistrationButton();
        setupResetPasswordButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(loginButton);
        this.add(registrationButton);
        this.add(resetPasswordButton);

        ImageIcon icon = new ImageIcon(ImagePathHolder.FRAME_ICON);
        this.setIconImage(icon.getImage());
        this.setTitle("Facebook Launch Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 200, 420, 420);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupResetPasswordButton() {
        resetPasswordButton.setText("Reset Password Page");
        resetPasswordButton.setBounds(110, 240, 200, 40);
        resetPasswordButton.setFocusable(false);
        resetPasswordButton.addActionListener(this);
    }

    private void setupRegistrationButton() {
        registrationButton.setText("Registration Page");
        registrationButton.setBounds(110, 160, 200, 40);
        registrationButton.setFocusable(false);
        registrationButton.addActionListener(this);
    }

    private void setupLoginButton() {
        loginButton.setText("Login Page");
        loginButton.setBounds(110, 80, 200, 40);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
    }
}
