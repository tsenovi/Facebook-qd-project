package org.vso.utils.implementations;

import org.vso.constants.ImagePathHolder;
import org.vso.models.dto.ImageUploadDTO;
import org.vso.utils.contracts.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtilImpl implements ImageUtil {

    @Override
    public Path getImageTargetDirectory(ImageUploadDTO imageUploadDTO) {
        Path imageTargetDirectory;
        Path imageTargetPath = Paths.get(
                ImagePathHolder.RESOURCES_IMAGES
                        + imageUploadDTO.getOwner().getId()
                        + File.separatorChar);
        try {
            imageTargetDirectory = Files.createDirectories(imageTargetPath);
        } catch (IOException e) {
            return null;
        }

        return imageTargetDirectory;
    }

    @Override
    public boolean copyToProjectDir(Path imageOriginalPath, Path imageTargetPath) {
        try {
            Files.copy(imageOriginalPath.normalize(), imageTargetPath.normalize());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public BufferedImage read(String imagePath) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            return null;
        }

        return bufferedImage;
    }
}
