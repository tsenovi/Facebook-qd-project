package org.vso.utils.implementations;

import org.vso.constants.ComponentText;
import org.vso.utils.contracts.ImageFileFilter;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ImageFileFilterImpl extends FileFilter implements ImageFileFilter {
    public final static String JPEG = "jpeg";
    public final static String JPG = "jpg";
    public final static String GIF = "gif";
    public final static String TIFF = "tiff";
    public final static String TIF = "tif";
    public final static String PNG = "png";

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }

        String extension = getExtension(file);
        if (extension != null) {
            return extension.equals(TIFF) ||
                    extension.equals(TIF) ||
                    extension.equals(GIF) ||
                    extension.equals(JPEG) ||
                    extension.equals(JPG) ||
                    extension.equals(PNG);
        }
        return false;
    }

    @Override
    public String getDescription() {
        return ComponentText.IMAGE.getText();
    }

    @Override
    public String getExtension(File file) {
        String extension = null;
        String fileName = file.getName();
        int indexOfDot = fileName.lastIndexOf(ComponentText.DOT.getText());

        if (indexOfDot > 0 && indexOfDot < fileName.length() - 1) {
            extension = fileName.substring(indexOfDot + 1).toLowerCase();
        }

        return extension;
    }
}
