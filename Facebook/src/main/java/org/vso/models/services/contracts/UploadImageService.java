package org.vso.models.services.contracts;

import org.vso.models.dto.ImageUploadDTO;

public interface UploadImageService {
    boolean upload(ImageUploadDTO imageUploadDTO);
}
