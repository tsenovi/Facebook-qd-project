package org.vso.views.implementations;

import org.vso.constants.*;
import org.vso.presenters.contracts.RegistrationPresenter;
import org.vso.presenters.implementations.RegistrationPresenterImpl;
import org.vso.utils.contracts.EmailValidator;
import org.vso.utils.implementations.EmailValidatorImpl;
import org.vso.views.contracts.RegistrationView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationViewImpl extends JFrame implements ActionListener, RegistrationView {
    private final JLabel errorEmailMsg;
    private final JLabel emailLabel;
    private final JTextField emailField;
    private final JLabel passwordLabel;
    private final JPasswordField passwordField;
    private final JLabel errorPasswordConfirmMsg;
    private final JLabel passwordConfirmLabel;
    private final JPasswordField passwordConfirmField;
    private final JLabel firstNameLabel;
    private final JTextField firstNameField;
    private final JLabel lastNameLabel;
    private final JTextField lastNameField;
    private final JLabel ageLabel;
    private final JTextField ageField;
    private final JLabel errorAgeMsg;
    private final JButton registrationButton;
    private final JButton returnButton;
    private final RegistrationPresenter registrationPresenter;
    private final EmailValidator emailValidator;

    public RegistrationViewImpl() {
        this.errorEmailMsg = new JLabel();
        this.emailLabel = new JLabel();
        this.emailField = new JTextField();
        this.passwordLabel = new JLabel();
        this.passwordField = new JPasswordField();
        this.errorPasswordConfirmMsg = new JLabel();
        this.passwordConfirmLabel = new JLabel();
        this.passwordConfirmField = new JPasswordField();
        this.firstNameLabel = new JLabel();
        this.firstNameField = new JTextField();
        this.lastNameLabel = new JLabel();
        this.lastNameField = new JTextField();
        this.ageLabel = new JLabel();
        this.ageField = new JTextField();
        this.errorAgeMsg = new JLabel();
        this.registrationButton = new JButton();
        this.returnButton = new JButton();

        this.registrationPresenter = new RegistrationPresenterImpl(this);
        this.emailValidator = new EmailValidatorImpl();

        setupComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (registrationButton.equals(source)) {
            registrationPresenter.onRegistrationButtonClicked();
        } else if (returnButton.equals(source)) {
            returnToLaunchPage();
        }
    }

    @Override
    public void getUserRegistrationInfo(RegistrationPresenterImpl.UserRegistrationListener listener) {
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String confirmedPassword = String.valueOf(passwordConfirmField.getPassword());
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        int age;
        try {
            age = Integer.parseInt(ageField.getText());
        } catch (NumberFormatException nfe) {
            age = 0;
        }

        listener.onUserRegistrationDataEntered(
                email,
                password,
                confirmedPassword,
                firstName,
                lastName,
                age
        );
    }

    @Override
    public void showUserExistsMsg() {
        showMessage(RegistrationStatus.EMAIL_EXISTS.getText());
    }

    @Override
    public void hideRegistrationPage() {
        this.dispose();
    }

    @Override
    public void showRegistrationSuccessful() {
        showMessage(RegistrationStatus.REGISTRATION_SUCCESSFUL.getText());
    }

    @Override
    public void showRegistrationFailed() {
        showMessage(RegistrationStatus.REGISTRATION_FAILED.getText());
    }

    private void returnToLaunchPage() {
        this.dispose();
        LaunchPage.getInstance().setVisible(true);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                RegistrationStatus.REGISTRATION_STATUS.getText(),
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void setupComponents() {
        setupErrorEmailLabel();
        setupEmailLabel();
        setupEmailField();
        setupPasswordLabel();
        setupPasswordField();
        setupConfirmPasswordLabel();
        setupConfirmPasswordField();
        setupErrorPasswordConfirmLabel();
        setupFirstNameLabel();
        setupFirstNameField();
        setupLastNameLabel();
        setupLastNameField();
        setupAgeLabel();
        setupAgeField();
        setupErrorAgeLabel();
        setupRegistrationButton();
        setupReturnButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(errorEmailMsg);
        this.add(emailLabel);
        this.add(emailField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(passwordConfirmLabel);
        this.add(passwordConfirmField);
        this.add(errorPasswordConfirmMsg);
        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(ageLabel);
        this.add(ageField);
        this.add(errorAgeMsg);
        this.add(registrationButton);
        this.add(returnButton);

        ImageIcon icon = new ImageIcon(ImagePathHolder.FRAME_ICON);
        this.setIconImage(icon.getImage());
        this.setTitle(ComponentText.REGISTER.getText());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 150, 420, 630);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupReturnButton() {
        returnButton.setText(ComponentText.RETURN.getText());
        returnButton.setBounds(50, 540, 300, 40);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    private void setupRegistrationButton() {
        registrationButton.setText(ComponentText.REGISTER.getText());
        registrationButton.setBounds(50, 480, 300, 40);
        registrationButton.setFocusable(false);
        registrationButton.addActionListener(this);
    }

    private void setupErrorAgeLabel() {
        errorAgeMsg.setText(ComponentText.AGE_REQUIREMENT.getText());
        errorAgeMsg.setForeground(this.getBackground());
        errorAgeMsg.setBounds(110, 440, 220, 40);
    }

    private void setupAgeField() {
        ageField.setBounds(170, 400, 180, 40);
        ageField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                validateAgeInput();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateAgeInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateAgeInput();
            }
        });
    }

    private void setupAgeLabel() {
        ageLabel.setText(ComponentText.AGE.getText());
        ageLabel.setBounds(50, 400, 80, 40);
    }

    private void setupLastNameField() {
        lastNameField.setBounds(170, 340, 180, 40);
    }

    private void setupLastNameLabel() {
        lastNameLabel.setText(ComponentText.LAST_NAME.getText());
        lastNameLabel.setBounds(50, 340, 80, 40);
    }

    private void setupFirstNameField() {
        firstNameField.setBounds(170, 280, 180, 40);
    }

    private void setupFirstNameLabel() {
        firstNameLabel.setText(ComponentText.FIRST_NAME.getText());
        firstNameLabel.setBounds(50, 280, 80, 40);
    }

    private void setupErrorPasswordConfirmLabel() {
        errorPasswordConfirmMsg.setText(ComponentText.PASSWORDS_MUST_MATCH.getText());
        errorPasswordConfirmMsg.setForeground(this.getBackground());
        errorPasswordConfirmMsg.setBounds(170, 230, 140, 40);
    }

    private void setupConfirmPasswordField() {
        passwordConfirmField.setBounds(170, 180, 180, 40);
        passwordConfirmField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                validatePasswordInput();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validatePasswordInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validatePasswordInput();
            }
        });
    }

    private void setupConfirmPasswordLabel() {
        passwordConfirmLabel.setText(ComponentText.CONFIRM_PASSWORD.getText());
        passwordConfirmLabel.setBounds(50, 180, 120, 40);
    }

    private void setupPasswordField() {
        passwordField.setBounds(170, 120, 180, 40);
    }

    private void setupPasswordLabel() {
        passwordLabel.setText(ComponentText.PASSWORD.getText());
        passwordLabel.setBounds(50, 120, 80, 40);
    }

    private void setupEmailField() {
        emailField.setBounds(170, 60, 180, 40);
        emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                validateEmailInput();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateEmailInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateEmailInput();
            }
        });
    }

    private void setupEmailLabel() {
        emailLabel.setText(ComponentText.EMAIL.getText());
        emailLabel.setBounds(50, 60, 80, 40);
    }

    private void setupErrorEmailLabel() {
        errorEmailMsg.setText(ComponentText.INVALID_EMAIL_INPUT.getText());
        errorEmailMsg.setForeground(this.getBackground());
        errorEmailMsg.setBounds(170, 10, 140, 40);
    }

    private void validateAgeInput() {
        int userAge;
        try {
            userAge = Integer.parseInt(ageField.getText());
            if (userAge >= Participant.MIN_AGE) {
                errorAgeMsg.setForeground(this.getBackground());
            } else {
                errorAgeMsg.setForeground(Color.RED);
            }
        } catch (NumberFormatException nfe) {
            errorAgeMsg.setForeground(Color.RED);
        }
    }

    private void validatePasswordInput() {
        if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordConfirmField.getPassword()))) {
            errorPasswordConfirmMsg.setForeground(this.getBackground());
        } else {
            errorPasswordConfirmMsg.setForeground(Color.RED);
        }
    }

    private void validateEmailInput() {
        if (emailValidator.isValidEmail(emailField.getText())) {
            errorEmailMsg.setForeground(this.getBackground());
        } else {
            errorEmailMsg.setForeground(Color.RED);
        }
    }
}
