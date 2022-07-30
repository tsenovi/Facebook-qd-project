package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.constants.ImagePathHolder;
import org.vso.models.data.User;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.presenters.implementations.FriendshipImpl;
import org.vso.presenters.implementations.SearchPresenterImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SentAFriendRequestView extends JFrame implements ActionListener {
    private final JLabel nameLabel;
    private final JTextField nameField;
    private final JButton sentButton;
    private final JButton returnButton;
    private final User loggedUser;
    private final FriendshipImpl friendship;
    private final SearchPresenterImpl searchPresenter;

    public SentAFriendRequestView() {
        this.nameLabel = new JLabel();
        this.nameField = new JTextField();
        this.sentButton = new JButton();
        this.returnButton = new JButton();
        this.loggedUser = AuthenticationServiceImpl.getInstance().getLoggedUser();
        this.friendship = new FriendshipImpl();
        this.searchPresenter = new SearchPresenterImpl();
        setupComponents();
    }

    private void setupComponents() {
        setupNameLabel();
        setupNameField();
        setupSentButton();
        setupReturnButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(nameLabel);
        this.add(nameField);
        this.add(sentButton);
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

    private void setupReturnButton() {
        returnButton.setText("return");
        returnButton.setBounds(50, 260, 300, 40);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    private void setupSentButton() {
        sentButton.setText("sent");
        sentButton.setBounds(50, 200, 300, 40);
        sentButton.setFocusable(false);
        sentButton.addActionListener(this);
    }

    private void setupNameField() {
        nameField.setBounds(150, 120, 200, 40);
    }

    private void setupNameLabel() {
        nameLabel.setText("Search friend: ");
        nameLabel.setBounds(50, 120, 80, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (sentButton.equals(source)){
            friendship.sendFriendRequest(loggedUser, searchPresenter.search(nameField.getText()));
        }else if (returnButton.equals(source)){
            navigateToProfilePage();
        }
    }

    private void navigateToProfilePage(){
        this.dispose();
        new ProfilePage();
    }
}
