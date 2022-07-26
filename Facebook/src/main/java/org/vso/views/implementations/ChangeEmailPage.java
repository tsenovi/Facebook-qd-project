package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.constants.ImagePathHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeEmailPage extends JFrame implements ActionListener {
    private static ChangeEmailPage instance;
    private final JLabel newEmailLabel;
    private final JTextField newEmailField;
    private final JLabel repeatEmailLabel;
    private final JTextField repeatEmailField;

    public ChangeEmailPage(){
        this.newEmailLabel  = new JLabel();
        this.newEmailField = new JTextField();
        this.repeatEmailLabel = new JLabel();
        this.repeatEmailField = new JTextField();
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
        setupFrame();
    }

    private void setupFrame() {
        this.add(newEmailLabel);
        this.add(newEmailField);
        this.add(repeatEmailLabel);
        this.add(repeatEmailField);

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
        newEmailLabel.setBounds(50, 50, 200, 200);
    }

    private void setupNewEmailField() {
        newEmailField.setBounds(200, 50, 200, 30);
    }

    private void setupRepeatEmailLabel() {
        repeatEmailLabel.setText("repeat email: ");
        repeatEmailLabel.setBounds(50, 80, 200, 200);
    }

    private void setupRepeatEmailField() {
        repeatEmailField.setBounds(200, 80, 200, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
