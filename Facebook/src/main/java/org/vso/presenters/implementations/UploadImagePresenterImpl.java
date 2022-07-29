package org.vso.presenters.implementations;

import org.vso.models.data.User;
import org.vso.models.dto.ImageUploadDTO;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.contracts.UploadImageService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;
import org.vso.models.services.implementations.UploadImageServiceImpl;
import org.vso.presenters.contracts.UploadImagePresenter;
import org.vso.views.contracts.UploadImageView;

public class UploadImagePresenterImpl implements UploadImagePresenter {
    private final UploadImageView uploadImageView;

    private final UploadImageService uploadImageService;

    private final AuthenticationService authenticationService;

    public UploadImagePresenterImpl(UploadImageView uploadImageView) {
        this.uploadImageView = uploadImageView;
        this.uploadImageService = new UploadImageServiceImpl();
        this.authenticationService = AuthenticationServiceImpl.getInstance();
    }

    @Override
    public void onSaveButtonClicked() {
        uploadImageView.getUploadedImageInfo((fileName, filePath, fileDescription) -> {
            User loggedUser = authenticationService.getLoggedUser();
            ImageUploadDTO imageUploadDTO = new ImageUploadDTO(loggedUser, fileName, filePath, fileDescription);
            onUploadedImageDataReceived(imageUploadDTO);
        });
    }

    private void onUploadedImageDataReceived(ImageUploadDTO imageUploadDTO) {
        boolean uploadSuccessful = uploadImageService.upload(imageUploadDTO);
        if (uploadSuccessful) {
            uploadImageView.showUploadImageSuccessful();
        } else {
            uploadImageView.showUploadImageFailed();
        }
    }

    public interface UploadedImageListener {
        void onImageDataReceived(String fileName, String filePath, String fileDescription);
    }
}
