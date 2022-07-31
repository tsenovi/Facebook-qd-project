package org.vso.utils.contracts;

import org.vso.models.dto.ImageUploadDTO;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public interface ImageUtil {
    Path getImageTargetDirectory(ImageUploadDTO imageUploadDTO);

    boolean copyToProjectDir(Path imageOriginalPath, Path imageTargetPath);

    BufferedImage read(String imagePath);
}
