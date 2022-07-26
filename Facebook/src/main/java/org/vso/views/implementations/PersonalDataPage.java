package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.constants.ImagePathHolder;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonalDataPage extends JFrame implements ActionListener {
    private static PersonalDataPage instance;
    private final JLabel firstNameLabel;
    private final JLabel lastNameLabel;
    private final JLabel ageLabel;
    private final JLabel emailLabel;
    private final AuthenticationService authenticationService;

    public PersonalDataPage(){
        this.firstNameLabel = new JLabel();
        this.lastNameLabel = new JLabel();
        this.ageLabel = new JLabel();
        this.emailLabel = new JLabel();
        this.authenticationService = AuthenticationServiceImpl.getInstance();
        setupComponents();
    }

    public static PersonalDataPage getInstance() {
        if (instance == null) instance = new PersonalDataPage();
        return instance;
    }

    private void setupComponents(){
        setupFirstNameLabel();
        setupLastNameLabel();
        setupAgeLabel();
        setupEmailLabel();
        setupFrame();
    }

    private void setupFirstNameLabel(){
        firstNameLabel.setText("First name: " + authenticationService.getLoggedUser().getFirstName());
        firstNameLabel.setBounds(50, 10, 220, 40);
    }

    private void setupLastNameLabel(){
        lastNameLabel.setText("Last name: " + authenticationService.getLoggedUser().getLastName());
        lastNameLabel.setBounds(50, 40, 220, 40);}

    private void setupAgeLabel(){
        ageLabel.setText("Age: " + authenticationService.getLoggedUser().getAge());
        ageLabel.setBounds(50, 70, 220, 40);
    }

    private void setupEmailLabel(){
        emailLabel.setText("Email: " + authenticationService.getLoggedUser().getEmail());
        emailLabel.setBounds(50, 100, 220, 40);
    }

    private void setupFrame(){
        this.add(firstNameLabel);
        this.add(lastNameLabel);
        this.add(ageLabel);
        this.add(emailLabel);

        ImageIcon icon = new ImageIcon(ImagePathHolder.FRAME_ICON);
        this.setIconImage(icon.getImage());
        this.setTitle(ComponentText.APP_TITLE.getText());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 200, 420, 420);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
