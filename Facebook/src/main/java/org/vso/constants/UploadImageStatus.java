package org.vso.constants;

public enum UploadImageStatus {

    UPLOAD_IMAGE_STATUS("Upload Image Status"),
    UPLOAD_SUCCESSFUL("Upload Successful!"),
    UPLOAD_FAILED("Upload Failed!");

    private final String text;

    UploadImageStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
