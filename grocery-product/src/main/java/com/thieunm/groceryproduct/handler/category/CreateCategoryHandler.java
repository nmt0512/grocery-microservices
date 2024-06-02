package com.thieunm.groceryproduct.handler.category;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryproduct.client.cloudinary.ICloudinaryClient;
import com.thieunm.groceryproduct.client.cloudinary.dto.request.UploadImageClientRequest;
import com.thieunm.groceryproduct.client.cloudinary.dto.response.UploadImageClientResponse;
import com.thieunm.groceryproduct.dto.request.category.CreateCategoryRequest;
import com.thieunm.groceryproduct.dto.response.category.CategoryResponse;
import com.thieunm.groceryproduct.dto.response.category.CreateCategoryResponse;
import com.thieunm.groceryproduct.entity.Category;
import com.thieunm.groceryproduct.repository.CategoryRepository;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CreateCategoryHandler extends CommandHandler<CreateCategoryRequest, CreateCategoryResponse> {

    private final CategoryRepository categoryRepository;
    private final ICloudinaryClient cloudinaryClient;

    @Value("${cloudinary.category-prefix-path}")
    private String cloudinaryCategoryPrefixPath;

    @Override
    public CreateCategoryResponse handle(CreateCategoryRequest requestData) {
        try {
            UploadImageClientResponse clientResponse = cloudinaryClient.uploadImage(UploadImageClientRequest.builder()
                    .file(requestData.getMultipartFile().getBytes())
                    .pathToFile(cloudinaryCategoryPrefixPath)
                    .build());
            Category category = Category.builder()
                    .name(requestData.getName())
                    .code(requestData.getCode())
                    .cloudinaryId(clientResponse.getCloudinaryId())
                    .imageUrl(clientResponse.getImageUrl())
                    .build();
            category = categoryRepository.save(category);
            return new CreateCategoryResponse(Mapper.map(category, CategoryResponse.class));
        } catch (IOException e) {
            return null;
        }
    }
}
