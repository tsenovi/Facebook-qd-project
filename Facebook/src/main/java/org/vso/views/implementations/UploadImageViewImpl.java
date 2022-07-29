package org.vso.views.implementations;

import org.vso.constants.ComponentText;
import org.vso.constants.UploadImageStatus;
import org.vso.presenters.contracts.UploadImagePresenter;
import org.vso.presenters.implementations.UploadImagePresenterImpl;
import org.vso.utils.contracts.ImageFileFilter;
import org.vso.utils.contracts.ImageUtil;
import org.vso.utils.implementations.ImageFileFilterImpl;
import org.vso.utils.implementations.ImageUtilImpl;
import org.vso.views.contracts.UploadImageView;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

public class UploadImageViewImpl extends BaseFrame implements UploadImageView {
    private final JButton chooseFileButton;
    private final JButton returnButton;
    private final JButton saveButton;
    private final JFileChooser fileChooser;
    private final ImageFileFilter imageFileFilter;
    private final ImageUtil imageUtil;
    private final JLabel selectedFilePath;
    private final JLabel selectedFileName;
    private final JTextArea selectedFileDescription;
    private final UploadImagePresenter uploadImagePresenter;

    public UploadImageViewImpl() {
        this.chooseFileButton = new JButton();
        this.returnButton = new JButton();
        this.saveButton = new JButton();
        this.fileChooser = new JFileChooser();
        this.imageFileFilter = new ImageFileFilterImpl();
        this.imageUtil = new ImageUtilImpl();
        this.selectedFilePath = new JLabel();
        this.selectedFileName = new JLabel();
        this.selectedFileDescription = new JTextArea();
        this.uploadImagePresenter = new UploadImagePresenterImpl(this);

        setupComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (chooseFileButton.equals(source)) {
            selectFileInput();
        } else if (saveButton.equals(source)) {
            uploadImagePresenter.onSaveButtonClicked();
        } else if (returnButton.equals(source)) {
            returnToProfilePage();
        }
    }

    @Override
    public void getUploadedImageInfo(UploadImagePresenterImpl.UploadedImageListener listener) {
        String fileName = selectedFileName.getText();
        String filePath = selectedFilePath.getText();
        String fileDescription = selectedFileDescription.getText();
        listener.onImageDataReceived(fileName, filePath, fileDescription);
    }

    @Override
    public void showUploadImageSuccessful() {
        showMessage(UploadImageStatus.UPLOAD_SUCCESSFUL.getText());
    }

    @Override
    public void showUploadImageFailed() {
        showMessage(UploadImageStatus.UPLOAD_FAILED.getText());
    }

    private void setupComponents() {
        setupUploadButton();
        setupReturnButton();
        setupSaveButton();
        setupFileChooser();
        setupSelectedFilePath();
        setupSelectedFileName();
        setupSelectedFileDescription();
        setupFrame();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                UploadImageStatus.UPLOAD_IMAGE_STATUS.getText(),
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void returnToProfilePage() {
        this.dispose();
        ProfileViewImpl.getInstance().setVisible();
    }

    private void setupReturnButton() {
        returnButton.setText(ComponentText.RETURN.getText());
        returnButton.setBounds(110, 525, 200, 40);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
    }

    private void setupSaveButton() {
        saveButton.setText(ComponentText.SAVE_FILE.getText());
        saveButton.setBounds(110, 475, 200, 40);
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);
    }

    private void setupUploadButton() {
        chooseFileButton.setText(ComponentText.SELECT_FILE.getText());
        chooseFileButton.setBounds(110, 425, 200, 40);
        chooseFileButton.setFocusable(false);
        chooseFileButton.addActionListener(this);
    }

    private void setupSelectedFileDescription() {
        selectedFileDescription.setText(ComponentText.YOUR_THOUGHTS.getText());
        selectedFileDescription.setBounds(20, 225, 370, 190);
    }

    private void setupSelectedFileName() {
        selectedFileName.setHorizontalAlignment(SwingConstants.CENTER);
        selectedFileName.setVerticalAlignment(SwingConstants.CENTER);
        selectedFileName.setBounds(20, 190, 380, 40);
    }

    private void setupSelectedFilePath() {
        selectedFilePath.setHorizontalAlignment(SwingConstants.CENTER);
        selectedFilePath.setVerticalAlignment(SwingConstants.CENTER);
        selectedFilePath.setBounds(20, 20, 380, 180);
    }

    private void setupFileComponents(File file, Path path) {
        selectedFileName.setText(file.getName());
        BufferedImage bufferedImage = imageUtil.read(path.toString());
        Image resizedImage = bufferedImage.getScaledInstance(
                selectedFilePath.getWidth(),
                selectedFilePath.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(resizedImage);
        selectedFilePath.setIcon(imageIcon);
        selectedFilePath.setText(path.toString());
    }

    private void setupFileChooser() {
        fileChooser.addChoosableFileFilter((FileFilter) imageFileFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
    }

    private void selectFileInput() {
        int option = fileChooser.showOpenDialog(getContentPane());
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Path path = file.toPath();
            setupFileComponents(file, path);
        }
    }

    private void setupFrame() {
        this.add(chooseFileButton);
        this.add(selectedFilePath);
        this.add(selectedFileName);
        this.add(selectedFileDescription);
        this.add(returnButton);
        this.add(saveButton);

        this.setTitle(ComponentText.UPLOAD_PHOTO.getText());
        this.setBounds(550, 100, 420, 620);
        this.setVisible(true);
    }
}
