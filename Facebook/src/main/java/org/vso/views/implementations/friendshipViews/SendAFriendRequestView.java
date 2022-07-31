package org.vso.views.implementations.friendshipViews;

import org.vso.presenters.implementations.FriendshipPresenter;
import org.vso.views.implementations.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SendAFriendRequestView extends BaseFrame {
    private static SendAFriendRequestView instance;
    private final JLabel messageLabel;
    private final JLabel firstNameLabel;
    private final JTextField firstNameField;
    private final JLabel lastNameLabel;
    private final JTextField lastNameField;
    private final JButton sentButton;
    private final FriendshipPresenter friendshipPresenter;

    public SendAFriendRequestView() {
        this.messageLabel = new JLabel();
        this.firstNameLabel = new JLabel();
        this.firstNameField = new JTextField();
        this.lastNameLabel = new JLabel();
        this.lastNameField = new JTextField();
        this.sentButton = new JButton();
        this.friendshipPresenter = new FriendshipPresenter();
        setupComponents();
    }

    public static SendAFriendRequestView getInstance(){
        if (instance == null){
            instance = new SendAFriendRequestView();
        }
        return instance;
    }

    private void setupComponents() {
        setupMessageLabel();
        setupFirstNameLabel();
        setupFirstNameField();
        setupLastNameLabel();
        setupLastNameField();
        setupSentButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(messageLabel);
        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(sentButton);

        this.setTitle("Sent a friend request");
        this.setVisible(true);
    }

    private void setupSentButton() {
        sentButton.setText("sent");
        sentButton.setBounds(50, 240, 300, 40);
        sentButton.setFocusable(false);
        sentButton.addActionListener(this);
    }

    private void setupLastNameField() {
        lastNameField.setBounds(150, 180, 200, 40);
    }

    private void setupLastNameLabel() {
        lastNameLabel.setText("last name: ");
        lastNameLabel.setBounds(50, 180, 100, 40);
    }

    private void setupFirstNameField() {
        firstNameField.setBounds(150, 120, 200, 40);
    }

    private void setupFirstNameLabel() {
        firstNameLabel.setText("first name: ");
        firstNameLabel.setBounds(50, 120, 100, 40);
    }

    private void setupMessageLabel() {
        messageLabel.setText("Sent a friend request to: ");
        messageLabel.setBounds(50, 90, 300, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (sentButton.equals(source)){
            friendshipPresenter.sentAFriendRequest(firstNameField.getText(), lastNameField.getText());
        }
    }
}
