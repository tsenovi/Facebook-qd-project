package org.vso.models.dto;

import org.vso.models.data.User;

public class ImageUploadDTO {

    private final User owner;
    private final String fileName;
    private String filePath;
    private final String fileDescription;

    public ImageUploadDTO(User owner, String fileName, String filePath, String fileDescription) {
        this.owner = owner;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileDescription = fileDescription;
    }

    public User getOwner() {
        return owner;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDescription() {
        return fileDescription;
    }
}
