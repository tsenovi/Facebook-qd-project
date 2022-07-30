package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.constants.LoginStatus;
import org.vso.presenters.contracts.LoginPresenter;
import org.vso.presenters.implementations.LoginPresenterImpl;
import org.vso.utils.contracts.EmailValidator;
import org.vso.utils.implementations.EmailValidatorImpl;
import org.vso.views.contracts.LoginView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginViewImpl extends BaseFrame implements LoginView {

    private static LoginViewImpl instance;
    private final JLabel errorMsg;
    private final JLabel emailLabel;
    private final JTextField emailField;
    private final JLabel passwordLabel;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton resetPasswordButton;
    private final JButton returnButton;
    private final LoginPresenter loginPresenter;
    private final EmailValidator emailValidator;

    private LoginViewImpl() {
        this.errorMsg = new JLabel();
        this.emailLabel = new JLabel();
        this.emailField = new JTextField();
        this.passwordLabel = new JLabel();
        this.passwordField = new JPasswordField();
        this.loginButton = new JButton();
        this.resetPasswordButton = new JButton();
        this.returnButton = new JButton();

        this.emailValidator = new EmailValidatorImpl();
        this.loginPresenter = new LoginPresenterImpl(this);

        setupComponents();
    }

    public static LoginViewImpl getInstance() {
        if(instance == null) instance = new LoginViewImpl();
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (loginButton.equals(source)) {
            loginPresenter.onLoginButtonClicked();
        } else if (resetPasswordButton.equals(source)) {
            //TODO - redirect to forgotten password page!
        } else if (returnButton.equals(source)) {
            returnToLaunchPage();
        }
    }

    @Override
    public void getUserLoginInfo(LoginPresenterImpl.UserLoginListener listener) {
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());
        listener.onUserLoginDataEntered(email, password);
    }

    @Override
    public void showLoginSuccessful() {
        showMessage(LoginStatus.LOGIN_SUCCESSFUL.getText());
    }

    @Override
    public void showLoginFailed() {
        showMessage(LoginStatus.LOGIN_FAILED.getText());
    }

    @Override
    public void hideLoginPage() {
        this.dispose();
    }

    @Override
    public void showLoginPage() {
        this.setVisible(true);
    }

    private void returnToLaunchPage() {
        this.dispose();
        LaunchPage.getInstance().setVisible(true);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                LoginStatus.LOGIN_STATUS.getText(),
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void setupComponents() {
        setupErrorLabel();
        setupEmailLabel();
        setupEmailField();
        setupPasswordLabel();
        setupPasswordField();
        setupLoginButton();
        setupResetPasswordButton();
        setupReturnButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(errorMsg);
        this.add(emailLabel);
        this.add(emailField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginButton);
        this.add(resetPasswordButton);
        this.add(returnButton);

        this.setTitle(ComponentText.LOGIN.getText());
        this.setVisible(true);
    }

    private void setupReturnButton() {
        returnButton.setText(ComponentText.RETURN.getText());
        returnButton.setBounds(50, 320, 300, 40);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    private void setupResetPasswordButton() {
        resetPasswordButton.setText(ComponentText.FORGOT_PASSWORD.getText());
        resetPasswordButton.setBounds(50, 260, 300, 40);
        resetPasswordButton.setFocusable(false);
        resetPasswordButton.addActionListener(this);
    }

    private void setupLoginButton() {
        loginButton.setText(ComponentText.LOGIN.getText());
        loginButton.setBounds(50, 200, 300, 40);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
    }

    private void setupPasswordField() {
        passwordField.setBounds(150, 120, 200, 40);
    }

    private void setupPasswordLabel() {
        passwordLabel.setText(ComponentText.PASSWORD.getText());
        passwordLabel.setBounds(50, 120, 80, 40);
    }

    private void setupEmailField() {
        emailField.setBounds(150, 60, 200, 40);
        emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput();
            }
        });
    }

    private void setupEmailLabel() {
        emailLabel.setText(ComponentText.EMAIL.getText());
        emailLabel.setBounds(50, 60, 80, 40);
    }

    private void setupErrorLabel() {
        errorMsg.setText(ComponentText.INVALID_EMAIL_INPUT.getText());
        errorMsg.setForeground(this.getBackground());
        errorMsg.setBounds(150, 10, 140, 40);
    }

    private void validateInput() {
        if (emailValidator.isValidEmail(emailField.getText())) {
            errorMsg.setForeground(this.getBackground());
        } else {
            errorMsg.setForeground(Color.RED);
        }
    }
}
