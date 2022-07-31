package org.vso.views.implementations;

import org.vso.presenters.implementations.ProfilePresenterImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ProfileOptionsScreen extends BaseFrame{
    private final JButton logoutButton;
    private final JButton uploadPhotoButton;
    private final JButton searchButton;
    private final JButton sentAFriendRequestButton;
    private final JButton yourFriendRequestButton;
    private final ProfilePresenterImpl profilePresenter;

    public ProfileOptionsScreen() {
        this.logoutButton = new JButton();
        this.uploadPhotoButton = new JButton();
        this.searchButton = new JButton();
        this.sentAFriendRequestButton = new JButton();
        this.yourFriendRequestButton = new JButton();
        this.profilePresenter = new ProfilePresenterImpl(ProfileViewImpl.getInstance());
        setupComponents();
    }

    private void setupComponents() {
        setupLogoutButton();
        setupUploadPhotoButton();
        setupSearchButton();
        setupSentAFriendRequestButton();
        setupYourFriendRequestButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(logoutButton);
        this.add(uploadPhotoButton);
        this.add(searchButton);
        this.add(sentAFriendRequestButton);
        this.add(yourFriendRequestButton);

        this.setTitle("Profile options");
        this.setVisible(true);
    }

    private void setupYourFriendRequestButton() {
        yourFriendRequestButton.setText("your friend request");
        yourFriendRequestButton.setBounds(50, 50, 200, 40);
        yourFriendRequestButton.setFocusable(false);
        yourFriendRequestButton.addActionListener(this);
    }

    private void setupSentAFriendRequestButton() {
        sentAFriendRequestButton.setText("sent a friend request");
        sentAFriendRequestButton.setBounds(50, 100, 200, 40);
        sentAFriendRequestButton.setFocusable(false);
        sentAFriendRequestButton.addActionListener(this);
    }

    private void setupSearchButton() {
        searchButton.setText("search");
        searchButton.setBounds(50, 150, 200, 40);
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);
    }

    private void setupUploadPhotoButton() {
        uploadPhotoButton.setText("upload photo");
        uploadPhotoButton.setBounds(50, 200, 200, 40);
        uploadPhotoButton.setFocusable(false);
        uploadPhotoButton.addActionListener(this);
    }

    private void setupLogoutButton() {
        logoutButton.setText("logout");
        logoutButton.setBounds(50, 250, 200, 40);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (logoutButton.equals(source)){
            profilePresenter.runLogoutProcess();
        }else if (uploadPhotoButton.equals(source)){
            profilePresenter.navigateToUploadPhotoView();
        }else if (searchButton.equals(source)){
            profilePresenter.navigateToSearchPage();
        }else if (sentAFriendRequestButton.equals(source)){
            profilePresenter.navigateToSentAFriendRequest();
        }else if (yourFriendRequestButton.equals(source)){
            profilePresenter.navigateToRespondingView();
        }
    }
}
