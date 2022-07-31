package org.vso.views.implementations.searchViews;

import org.vso.presenters.implementations.SearchPresenter;
import org.vso.views.implementations.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class SearchPage extends BaseFrame {
    private static SearchPage instance;
    private final JLabel firstNameLabel;
    private final JTextField firstNameField;
    private final JLabel lastNameLabel;
    private final JTextField lastNameField;
    private final JButton searchButton;
    private final SearchPresenter searchPresenter;

    public SearchPage() {
        this.firstNameLabel = new JLabel();
        this.firstNameField = new JTextField();
        this.lastNameLabel = new JLabel();
        this.lastNameField = new JTextField();
        this.searchButton = new JButton();
        this.searchPresenter = new SearchPresenter();
        setupComponents();
    }

    public static SearchPage getInstance(){
        if (instance == null){
            instance = new SearchPage();
        }
        return instance;
    }

    private void setupComponents() {
        setupFirstNameLabel();
        setupFirstNameField();
        setupLastNameLabel();
        setupLastNameField();
        setupSearchButton();
        setupFrame();
    }

    private void setupFrame() {
        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(searchButton);

        this.setTitle("Search");
        this.setBounds(550, 150, 420, 630);
        this.setVisible(true);
    }

    private void setupSearchButton() {
        searchButton.setText("search");
        searchButton.setBounds(50, 300, 300, 40);
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);
    }

    private void setupLastNameField() {
        lastNameField.setBounds(150, 200, 200, 40);
    }

    private void setupLastNameLabel() {
        lastNameLabel.setText("last name: ");
        lastNameLabel.setBounds(50, 200, 80, 40);
    }

    private void setupFirstNameField() {
        firstNameField.setBounds(150, 120, 200, 40);
    }

    private void setupFirstNameLabel() {
        firstNameLabel.setText("first name: ");
        firstNameLabel.setBounds(50, 120, 80, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (searchButton.equals(source)){
           searchPresenter.search(firstNameField.getText(), lastNameField.getText());
        }
    }
}
