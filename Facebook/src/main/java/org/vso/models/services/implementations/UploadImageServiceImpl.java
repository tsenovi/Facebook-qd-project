package org.vso.models.services.implementations;

import org.vso.models.dao.contracts.ImageDao;
import org.vso.models.dao.implementations.ImageDaoImpl;
import org.vso.models.data.Image;
import org.vso.models.data.User;
import org.vso.models.dto.ImageUploadDTO;
import org.vso.models.services.contracts.UploadImageService;
import org.vso.utils.contracts.ImageUtil;
import org.vso.utils.implementations.ImageUtilImpl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadImageServiceImpl implements UploadImageService {

    private final ImageDao<Image> imageDao;
    private final ImageUtil imageUtil;

    public UploadImageServiceImpl() {
        this.imageDao = new ImageDaoImpl();
        this.imageUtil = new ImageUtilImpl();
    }

    @Override
    public boolean upload(ImageUploadDTO imageUploadDTO) {
        Path imageOriginalPath = Path.of(imageUploadDTO.getFilePath());
        Path imageTargetDirectory = imageUtil.getImageTargetDirectory(imageUploadDTO);
        Path imageTargetPath = Paths.get(
                imageTargetDirectory.toString()
                        + File.separatorChar
                        + imageUploadDTO.getFileName());

        boolean copySuccessful = imageUtil.copyToProjectDir(imageOriginalPath, imageTargetPath);
        if (copySuccessful) {
            imageUploadDTO.setFilePath(imageTargetPath.toString());
            Image image = mapImageDTOtoImageEntity(imageUploadDTO);
            imageDao.save(image);
            return true;
        }

        return false;
    }

    private Image mapImageDTOtoImageEntity(ImageUploadDTO imageUploadDTO) {
        User owner = imageUploadDTO.getOwner();
        String fileDescription = imageUploadDTO.getFileDescription();
        String filePath = imageUploadDTO.getFilePath();
        return new Image(owner, fileDescription, filePath);
    }
}
