package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.constants.ImagePathHolder;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.utils.contracts.EmailValidator;
import org.vso.utils.implementations.EmailValidatorImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeEmailPage extends JFrame implements ActionListener{
    private static ChangeEmailPage instance;
    private final JLabel newEmailLabel;
    private final JTextField newEmailField;
    private final JLabel repeatEmailLabel;
    private final JTextField repeatEmailField;
    private final JButton editEmailButton;
    private final EmailValidator emailValidator;
    private final AuthenticationService authenticationService;

    public ChangeEmailPage(){
        this.newEmailLabel  = new JLabel();
        this.newEmailField = new JTextField();
        this.repeatEmailLabel = new JLabel();
        this.repeatEmailField = new JTextField();
        this.editEmailButton = new JButton();
        this.emailValidator = new EmailValidatorImpl();
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        setupComponents();
    }

    public static ChangeEmailPage getInstance(){
        if (instance == null){
            instance = new ChangeEmailPage();
        }
        return instance;
    }

    private void setupComponents(){
        setupNewEmailLabel();
        setupNewEmailField();
        setupRepeatEmailLabel();
        setupRepeatEmailField();
        setupEditEmailButton();
        setupFrame();
    }



    private void setupFrame() {
        this.add(newEmailLabel);
        this.add(newEmailField);
        this.add(repeatEmailLabel);
        this.add(repeatEmailField);
        this.add(editEmailButton);

        ImageIcon icon = new ImageIcon(ImagePathHolder.FRAME_ICON);
        this.setIconImage(icon.getImage());
        this.setTitle(ComponentText.APP_TITLE.getText());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 200, 420, 420);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupNewEmailLabel() {
        newEmailLabel.setText("new email: ");
        newEmailLabel.setBounds(50, 120, 80, 40);
    }

    private void setupNewEmailField() {
        newEmailField.setBounds(150, 120, 200, 40);
    }

    private void setupRepeatEmailLabel() {
        repeatEmailLabel.setText("repeat email: ");
        repeatEmailLabel.setBounds(50, 200, 80, 40);
    }

    private void setupRepeatEmailField() {
        repeatEmailField.setBounds(150, 200, 200, 40);
    }

    private void setupEditEmailButton() {
        editEmailButton.setText("Edit email");
        editEmailButton.setBounds(50, 300, 300, 40);
        editEmailButton.setFocusable(false);
        editEmailButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (emailValidator.isValidEmail(newEmailField.getText()) &&
        newEmailField.getText().equals(repeatEmailField.getText())&&
        editEmailButton.equals(source)){
            authenticationService.getLoggedUser().setEmail(newEmailField.getText());
            this.dispose();
        }
    }
}
