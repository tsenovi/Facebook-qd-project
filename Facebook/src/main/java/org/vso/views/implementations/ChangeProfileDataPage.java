package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.constants.ImagePathHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeProfileDataPage extends JFrame implements ActionListener{
    private static ChangeProfileDataPage instance;
    private final JButton editPasswordButton;
    private final JButton editEmailButton;
    private final JButton returnButton;

    public ChangeProfileDataPage(){
        this.editPasswordButton = new JButton();
        this.editEmailButton = new JButton();
        this.returnButton = new JButton();
        setupComponents();
    }

    public static ChangeProfileDataPage getInstance(){
        if (instance == null){
            instance = new ChangeProfileDataPage();
        }
        return instance;
    }

    private void setupComponents(){
        setupPasswordButton();
        setupEmailButton();
        setupReturnButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(editPasswordButton);
        this.add(editEmailButton);
        this.add(returnButton);

        ImageIcon icon = new ImageIcon(ImagePathHolder.FRAME_ICON);
        this.setIconImage(icon.getImage());
        this.setTitle(ComponentText.APP_TITLE.getText());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 200, 420, 420);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupEmailButton() {
       editEmailButton.setText("Edit email");
       editEmailButton.setBounds(150, 50, 120, 40);
       editEmailButton.setFocusable(false);
       editEmailButton.addActionListener(this);
    }

    private void setupPasswordButton() {
        editPasswordButton.setText("Edit password");
        editPasswordButton.setBounds(150, 100, 120, 40);
        editPasswordButton.setFocusable(false);
        editPasswordButton.addActionListener(this);
    }

    private void setupReturnButton() {
        returnButton.setText("return");
        returnButton.setBounds(150, 150, 120, 40);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (this.editEmailButton.equals(source)){
            navigateToChangeEmailPage();
        }else if (this.editPasswordButton.equals(source)){
            navigateToChangePasswordPage();
        }else if (this.returnButton.equals(source)){
            navigateToProfilePage();
        }
    }

    private void navigateToChangeEmailPage(){
        this.dispose();
        new ChangeEmailPage();
    }

    private void navigateToChangePasswordPage(){
        this.dispose();
        new ChangePasswordPage();
    }

    private void navigateToProfilePage(){
        this.dispose();
        new ProfilePage();
    }
}
