package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.constants.ImagePathHolder;
import org.vso.models.services.contracts.ChangeProfileDataService;
import org.vso.models.services.implementations.ChangeProfileDataServiceImpl;

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
    private final JButton returnButton;
    private final ChangeProfileDataService changeProfileDataService;

    public ChangeEmailPage(){
        this.newEmailLabel  = new JLabel();
        this.newEmailField = new JTextField();
        this.repeatEmailLabel = new JLabel();
        this.repeatEmailField = new JTextField();
        this.editEmailButton = new JButton();
        this.returnButton = new JButton();
        this.changeProfileDataService = new ChangeProfileDataServiceImpl();
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
        setupReturnButton();
        setupFrame();
    }


    private void setupFrame() {
        this.add(newEmailLabel);
        this.add(newEmailField);
        this.add(repeatEmailLabel);
        this.add(repeatEmailField);
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

    private void setupNewEmailLabel() {
        newEmailLabel.setText("new email: ");
        newEmailLabel.setBounds(50, 90, 80, 40);
    }

    private void setupNewEmailField() {
        newEmailField.setBounds(150, 90, 200, 40);
    }

    private void setupRepeatEmailLabel() {
        repeatEmailLabel.setText("repeat email: ");
        repeatEmailLabel.setBounds(50, 150, 80, 40);
    }

    private void setupRepeatEmailField() {
        repeatEmailField.setBounds(150, 150, 200, 40);
    }

    private void setupEditEmailButton() {
        editEmailButton.setText("Edit email");
        editEmailButton.setBounds(50, 200, 300, 40);
        editEmailButton.setFocusable(false);
        editEmailButton.addActionListener(this);
    }

    private void setupReturnButton() {
        returnButton.setText("return");
        returnButton.setBounds(50, 250, 300, 40);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (editEmailButton.equals(source)){
            changeProfileDataService.editEmail(newEmailField.getText(),
                    repeatEmailField.getText());
        }else if (returnButton.equals(source)){
            navigateToChangeProfileDataPage();
        }
    }

    private void navigateToChangeProfileDataPage(){
        this.dispose();
        new ChangeProfileDataPage();
    }
}
