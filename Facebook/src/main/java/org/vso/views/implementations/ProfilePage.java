package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.constants.ImagePathHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePage extends JFrame implements ActionListener {
    private static ProfilePage instance;
    private final JButton personalDataButton;

    public ProfilePage(){
        this.personalDataButton = new JButton();
        setupComponents();
    }

    public static ProfilePage getInstance(){
        if (instance == null) instance = new ProfilePage();
        return instance;
    }

    private void setupComponents(){
        setupPersonalDataButton();
        setupFrame();
    }

    private void setupFrame(){
        this.add(personalDataButton);

        ImageIcon icon = new ImageIcon(ImagePathHolder.FRAME_ICON);
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
        personalDataButton.setBounds(110, 10, 200, 40);
        personalDataButton.setFocusable(false);
        personalDataButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (personalDataButton.equals(source)) {
            navigateToPersonalData();
        }
    }

    private void navigateToPersonalData() {
        this.dispose();
        new PersonalDataPage();
    }


}
