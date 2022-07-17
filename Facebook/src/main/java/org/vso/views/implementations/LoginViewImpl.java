package org.vso.views.implementations;

import org.vso.constants.ImagePathHolder;
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
import java.awt.event.ActionListener;

public class LoginViewImpl extends JFrame implements ActionListener, LoginView {

    private final JLabel errorMsg;
    private final JLabel emailLabel;
    private final JTextField emailField;
    private final JLabel passwordLabel;
    private final JPasswordField passwordField;
    private final JButton loginPageButton;
    private final JButton returnButton;
    private final LoginPresenter loginPresenter;
    private final EmailValidator emailValidator;

    public LoginViewImpl() {
        this.errorMsg = new JLabel();
        this.emailLabel = new JLabel();
        this.emailField = new JTextField();
        this.passwordLabel = new JLabel();
        this.passwordField = new JPasswordField();
        this.loginPageButton = new JButton();
        this.returnButton = new JButton();

        this.emailValidator = new EmailValidatorImpl();
        this.loginPresenter = new LoginPresenterImpl(this);

        setupComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (loginPageButton.equals(source)) {
            loginPresenter.onLoginButtonClicked();
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
        showMessage(String.valueOf(LoginStatus.LOGIN_SUCCESSFUL));
    }

    @Override
    public void showLoginFailed() {
        showMessage(String.valueOf(LoginStatus.LOGIN_FAILED));
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                String.valueOf(LoginStatus.LOGIN_STATUS),
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void returnToLaunchPage() {
        this.dispose();
        LaunchPage.getInstance().setVisible(true);
    }

    private void setupComponents() {
        setupErrorLabel();
        setupEmailLabel();
        setupEmailField();
        setupPasswordLabel();
        setupPasswordField();
        setupLoginButton();
        setupReturnButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(errorMsg);
        this.add(emailLabel);
        this.add(emailField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginPageButton);
        this.add(returnButton);

        ImageIcon icon = new ImageIcon(ImagePathHolder.FRAME_ICON);
        this.setIconImage(icon.getImage());
        this.setTitle("Facebook Login Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 200, 420, 420);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupReturnButton() {
        returnButton.setText("Go Back");
        returnButton.setBounds(50, 320, 300, 40);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    private void setupLoginButton() {
        loginPageButton.setText("Login");
        loginPageButton.setBounds(50, 240, 300, 40);
        loginPageButton.setFocusable(false);
        loginPageButton.addActionListener(this);
    }

    private void setupPasswordField() {
        passwordField.setBounds(150, 160, 200, 40);
    }

    private void setupPasswordLabel() {
        passwordLabel.setText("Password: ");
        passwordLabel.setBounds(50, 160, 80, 40);
    }

    private void setupEmailField() {
        emailField.setBounds(150, 80, 200, 40);
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
        emailLabel.setText("Email: ");
        emailLabel.setBounds(50, 80, 80, 40);
    }

    private void setupErrorLabel() {
        errorMsg.setText("Invalid email input!");
        errorMsg.setForeground(this.getBackground());
        errorMsg.setBounds(150, 25, 140, 40);
    }

    private void validateInput() {
        if (emailValidator.isValidEmail(emailField.getText())) {
            errorMsg.setForeground(this.getBackground());
        } else {
            errorMsg.setForeground(Color.RED);
        }
    }
}
