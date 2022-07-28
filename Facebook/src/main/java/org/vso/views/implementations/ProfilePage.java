package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePage extends JFrame implements ActionListener {
    private static ProfilePage instance;
    private final JButton personalDataButton;
    private final JButton editProfileButton;
    private final JButton returnButton;

    public ProfilePage(){
        this.personalDataButton = new JButton();
        this.editProfileButton = new JButton();
        this.returnButton = new JButton();
        setupComponents();
    }

    public static ProfilePage getInstance(){
        if (instance == null) instance = new ProfilePage();
        return instance;
    }

    private void setupComponents(){
        setupPersonalDataButton();
        setupEditProfileButton();
        setupReturnButton();
        setupFrame();
    }

    private void setupFrame(){
        this.add(personalDataButton);
        this.add(editProfileButton);
        this.add(returnButton);

        ImageIcon icon = new ImageIcon("img.png");
        this.setIconImage(icon.getImage());
        this.setTitle(ComponentText.APP_TITLE.getText());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 200, 420, 420);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupPersonalDataButton(){
        personalDataButton.setText("Personal data");
        personalDataButton.setBounds(110, 100, 200, 40);
        personalDataButton.setFocusable(false);
        personalDataButton.addActionListener(this);
    }

    private void setupEditProfileButton() {
        editProfileButton.setText("Edit profile");
        editProfileButton.setBounds(110, 180, 200, 40);
        editProfileButton.setFocusable(false);
        editProfileButton.addActionListener(this);
    }

    private void setupReturnButton() {
        returnButton.setText("return");
        returnButton.setBounds(110, 260, 200, 40);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (personalDataButton.equals(source)) {
            navigateToPersonalData();
        }else if(editProfileButton.equals(source)){
            navigateToChangeProfileDataPage();
        }else if (returnButton.equals(source)){
            navigateToIntermediatePage();
        }
    }

    private void navigateToPersonalData() {
        this.dispose();
        new PersonalDataPage();
    }

    private void navigateToChangeProfileDataPage() {
        this.dispose();
        new ChangeProfileDataPage();
    }

    private void navigateToIntermediatePage(){
        this.dispose();
        new IntermediatePage();
    }
}
