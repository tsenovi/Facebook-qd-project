package org.vso.views.implementations.friendshipViews;


import org.vso.models.data.User;
import org.vso.presenters.implementations.FriendshipPresenter;
import org.vso.views.implementations.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RespondingView extends BaseFrame {
    private final FriendshipPresenter friendshipPresenter;
    private int counter;

    public RespondingView() {
        this.friendshipPresenter = new FriendshipPresenter();
        setupComponents();
    }

    private void setupComponents() {
        showFriendRequestByUser(friendshipPresenter.getLoggedUser());
        setupFrame();
    }

    private void setupFrame() {
        this.setTitle("Respond");
        this.setVisible(true);
    }

    private void showFriendRequestByUser(User user){
        int x = 50;
        int yText = 20;
        int yButtons = 40;
        int widthText = 400;
        int widthButtons = 100;
        int heightText = 100;
        int height = 20;
        for (counter = 1; counter < user.getFriendShips().size(); counter++) {
            this.add(new JLabel(user.getFriendShips().get(counter).getSender().getFirstName() + " " +
                    user.getFriendShips().get(counter).getSender().getLastName()
                    + " send a friend request")).setBounds(x, yText, widthText,heightText);
            yText += 50;
            setupAcceptButton(user, x, yButtons, widthButtons, height);

            setupDeclinedButton(user, x, yButtons, widthButtons, height);
            yButtons += 50;
        }
    }

    private void setupDeclinedButton(User user, int x, int yButtons, int widthButtons, int height) {
        JButton declinedButton = new JButton("declined");
        declinedButton.setBounds(x +100, yButtons, widthButtons, height);
        this.add(declinedButton);
        declinedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friendshipPresenter.declinedFriendRequest(user.getFriendShips().get(counter - 1));
            }
        });
    }

    private void setupAcceptButton(User user, int x, int yButtons, int widthButtons, int height) {
        JButton acceptButton = new JButton("accept");
        acceptButton.setBounds(x, yButtons, widthButtons, height);
        this.add(acceptButton);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friendshipPresenter.acceptFriendRequest(user.getFriendShips().get(counter - 1));
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
