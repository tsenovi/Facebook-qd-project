package org.vso.utils.contracts;

import java.io.File;

public interface ImageFileFilter {
    boolean accept(File file);

    String getDescription();

    String getExtension(File file);
}
