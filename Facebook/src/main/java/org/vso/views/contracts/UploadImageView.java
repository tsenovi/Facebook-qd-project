package org.vso.views.contracts;

import org.vso.presenters.implementations.UploadImagePresenterImpl;

public interface UploadImageView {
    void getUploadedImageInfo(UploadImagePresenterImpl.UploadedImageListener listener);

    void showUploadImageSuccessful();

    void showUploadImageFailed();
}
