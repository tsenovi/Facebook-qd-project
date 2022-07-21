package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.constants.ImagePathHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage extends JFrame implements ActionListener {
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
            //TODO
            navigateToRegistrationPage();
        }
    }

    private void navigateToRegistrationPage() {
        this.dispose();
        new RegistrationViewImpl();
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

        ImageIcon icon = new ImageIcon(ImagePathHolder.FRAME_ICON);
        this.setIconImage(icon.getImage());
        this.setTitle(ComponentText.APP_TITLE.getText());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 200, 420, 420);
        this.setResizable(false);
        this.setLayout(null);
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
