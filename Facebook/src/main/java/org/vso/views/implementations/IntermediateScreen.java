package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.constants.ImagePathHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntermediateScreen extends JFrame implements ActionListener {
    private static IntermediateScreen instance;
    private final JButton profilePageButton;
    private final JButton profileWallButton;

    public IntermediateScreen(){
        this.profilePageButton = new JButton();
        this.profileWallButton = new JButton();
        setupComponents();
    }

    public static IntermediateScreen getInstance(){
        if (instance == null) instance = new IntermediateScreen();
        return instance;
    }

    private void setupComponents(){
        setupProfilePageButton();
        setupProfileWallButton();
        setupFrame();
    }

    private void setupFrame(){
        this.add(profilePageButton);
        this.add(profileWallButton);

        ImageIcon icon = new ImageIcon(ImagePathHolder.FRAME_ICON);
        this.setIconImage(icon.getImage());
        this.setTitle(ComponentText.APP_TITLE.getText());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 200, 420, 420);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupProfilePageButton(){
        profilePageButton.setText("Profile page");
        profilePageButton.setBounds(110, 100, 200, 40);
        profilePageButton.setFocusable(false);
        profilePageButton.addActionListener(this);
    }

    private void setupProfileWallButton(){
        profileWallButton.setText("Profile wall");
        profileWallButton.setBounds(110, 220, 200, 40);
        profileWallButton.setFocusable(false);
        profileWallButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (profilePageButton.equals(source)) {
            navigateToProfilePage();
        } else if (profileWallButton.equals(source)) {
            //TODO
            navigateToProfileWall();
        }
    }

    private void navigateToProfileWall() {
        this.dispose();
        new ProfileWall();
    }

    private void navigateToProfilePage() {
        this.dispose();
        new ProfilePage();
    }

}
