package com.thieunm.groceryproduct.client.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.thieunm.groceryproduct.client.cloudinary.dto.request.DeleteImageClientRequest;
import com.thieunm.groceryproduct.client.cloudinary.dto.request.UploadImageClientRequest;
import com.thieunm.groceryproduct.client.cloudinary.dto.response.DeleteImageClientResponse;
import com.thieunm.groceryproduct.client.cloudinary.dto.response.UploadImageClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class CloudinaryClient implements ICloudinaryClient {

    private final Cloudinary cloudinary;

    @Override
    @SneakyThrows
    public UploadImageClientResponse uploadImage(UploadImageClientRequest request) {
        Map uploadResponse = cloudinary.uploader().upload(request.getFile(), ObjectUtils.asMap(
                "folder", request.getPathToFile()
        ));
        log.info("Uploaded to Cloudinary with response: [{}]", uploadResponse);
        String imageUrl = uploadResponse.get("url").toString();
        String cloudinaryId = uploadResponse.get("public_id").toString();
        return UploadImageClientResponse.builder()
                .cloudinaryId(cloudinaryId)
                .imageUrl(imageUrl)
                .build();
    }

    @Override
    @SneakyThrows
    @Async
    public DeleteImageClientResponse deleteImage(DeleteImageClientRequest request) {
        ApiResponse deleteResponse = cloudinary.api().deleteResources(request.getCloudinaryIdList(), ObjectUtils.emptyMap());
        log.info("Deleted images in Cloudinary with response: [{}]", deleteResponse);
        return new DeleteImageClientResponse();
    }
}
