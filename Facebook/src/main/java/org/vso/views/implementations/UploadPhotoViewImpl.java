package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.presenters.implementations.UploadPhotoPresenter;
import org.vso.utils.implementations.ImageFileFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;

public class UploadPhotoViewImpl extends BaseFrame {

    private final UploadPhotoPresenter uploadPhotoPresenter;
    private final JButton chooseFileButton;
    private final JButton returnButton;
    private final JButton saveButton;
    private final JFileChooser fileChooser;
    private final ImageFileFilter imageFileFilter;
    private final JLabel selectedFilePath;

    public UploadPhotoViewImpl() {
        this.chooseFileButton = new JButton();
        this.returnButton = new JButton();
        this.saveButton = new JButton();
        this.fileChooser = new JFileChooser();
        this.imageFileFilter = new ImageFileFilter();
        this.selectedFilePath = new JLabel();
        this.uploadPhotoPresenter = new UploadPhotoPresenter(this);

        setupComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (chooseFileButton.equals(source)) {
            selectFileInput();
        } else if (saveButton.equals(source)) {
            uploadPhotoPresenter.onSaveButtonClicked();
        } else if (returnButton.equals(source)) {
            returnToProfilePage();
        }
    }

    private void selectFileInput() {
        int option = fileChooser.showOpenDialog(getContentPane());
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Path path = file.toPath();
            selectedFilePath.setText(path.toString());
        }
    }

    private void returnToProfilePage() {
        this.dispose();
        ProfileViewImpl.getInstance().setVisible();
    }

    private void setupComponents() {
        setupUploadButton();
        setupReturnButton();
        setupSaveButton();
        setupFileChooser();
        setupSelectedFilePath();
        setupFrame();
    }

    private void setupSelectedFilePath() {
        selectedFilePath.setHorizontalAlignment(SwingConstants.CENTER);
        selectedFilePath.setVerticalAlignment(SwingConstants.CENTER);
        selectedFilePath.setBounds(40, 150, 340, 40);
    }

    private void setupFileChooser() {
        fileChooser.addChoosableFileFilter(imageFileFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
    }

    private void setupReturnButton() {
        returnButton.setText(ComponentText.RETURN.getText());
        returnButton.setBounds(110, 300, 200, 40);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    private void setupSaveButton() {
        saveButton.setText(ComponentText.SAVE_FILE.getText());
        saveButton.setBounds(110, 200, 200, 40);
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);
    }

    private void setupUploadButton() {
        chooseFileButton.setText(ComponentText.SELECT_FILE.getText());
        chooseFileButton.setBounds(110, 100, 200, 40);
        chooseFileButton.setFocusable(false);
        chooseFileButton.addActionListener(this);
    }

    private void setupFrame() {
        this.add(chooseFileButton);
        this.add(selectedFilePath);
        this.add(returnButton);
        this.add(saveButton);

        this.setTitle(ComponentText.UPLOAD_PHOTO.getText());
    }
}
