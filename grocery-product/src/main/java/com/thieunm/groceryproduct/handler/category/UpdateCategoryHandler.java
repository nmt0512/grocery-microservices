package com.thieunm.groceryproduct.handler.category;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryproduct.client.cloudinary.ICloudinaryClient;
import com.thieunm.groceryproduct.client.cloudinary.dto.request.DeleteImageClientRequest;
import com.thieunm.groceryproduct.client.cloudinary.dto.request.UploadImageClientRequest;
import com.thieunm.groceryproduct.client.cloudinary.dto.response.UploadImageClientResponse;
import com.thieunm.groceryproduct.dto.request.category.UpdateCategoryRequest;
import com.thieunm.groceryproduct.dto.response.category.CategoryResponse;
import com.thieunm.groceryproduct.dto.response.category.UpdateCategoryResponse;
import com.thieunm.groceryproduct.entity.Category;
import com.thieunm.groceryproduct.repository.CategoryRepository;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateCategoryHandler extends CommandHandler<UpdateCategoryRequest, UpdateCategoryResponse> {

    private final CategoryRepository categoryRepository;
    private final ICloudinaryClient cloudinaryClient;

    @Value("${cloudinary.category-prefix-path}")
    private String cloudinaryCategoryPrefixPath;

    @Override
    public UpdateCategoryResponse handle(UpdateCategoryRequest requestData) {
        try {
            Category category = categoryRepository.findById(requestData.getId()).orElseThrow(NoSuchElementException::new);
            Category updatedCategory = Mapper.map(requestData, category);
            if (requestData.getImageUrl() == null) {
                cloudinaryClient.deleteImage(new DeleteImageClientRequest(List.of(updatedCategory.getCloudinaryId())));
                updatedCategory.setImageUrl(null);
                updatedCategory.setCloudinaryId(null);
            }
            if (requestData.getMultipartFile() != null) {
                UploadImageClientResponse clientResponse = cloudinaryClient.uploadImage(UploadImageClientRequest.builder()
                        .file(requestData.getMultipartFile().getBytes())
                        .pathToFile(cloudinaryCategoryPrefixPath)
                        .build());
                updatedCategory.setCloudinaryId(clientResponse.getCloudinaryId());
                updatedCategory.setImageUrl(clientResponse.getImageUrl());
            }
            updatedCategory = categoryRepository.save(updatedCategory);
            return new UpdateCategoryResponse(Mapper.map(updatedCategory, CategoryResponse.class));
        } catch (IOException e) {
            return null;
        }
    }
}
