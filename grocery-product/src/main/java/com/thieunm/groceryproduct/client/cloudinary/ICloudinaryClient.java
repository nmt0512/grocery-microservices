package com.thieunm.groceryproduct.client.cloudinary;

import com.thieunm.groceryproduct.client.cloudinary.dto.request.DeleteImageClientRequest;
import com.thieunm.groceryproduct.client.cloudinary.dto.request.UploadImageClientRequest;
import com.thieunm.groceryproduct.client.cloudinary.dto.response.DeleteImageClientResponse;
import com.thieunm.groceryproduct.client.cloudinary.dto.response.UploadImageClientResponse;

public interface ICloudinaryClient {
    UploadImageClientResponse uploadImage(UploadImageClientRequest request);

    DeleteImageClientResponse deleteImage(DeleteImageClientRequest request);
}
