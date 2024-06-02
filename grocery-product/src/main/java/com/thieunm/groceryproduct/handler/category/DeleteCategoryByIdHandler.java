package com.thieunm.groceryproduct.handler.category;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryproduct.client.cloudinary.ICloudinaryClient;
import com.thieunm.groceryproduct.client.cloudinary.dto.request.DeleteImageClientRequest;
import com.thieunm.groceryproduct.dto.request.category.DeleteCategoryByIdRequest;
import com.thieunm.groceryproduct.dto.response.category.DeleteCategoryByIdResponse;
import com.thieunm.groceryproduct.entity.Category;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.CategoryRepository;
import com.thieunm.groceryproduct.repository.ImageRepository;
import com.thieunm.groceryproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteCategoryByIdHandler extends CommandHandler<DeleteCategoryByIdRequest, DeleteCategoryByIdResponse> {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ICloudinaryClient cloudinaryClient;

    @Override
    @Transactional
    public DeleteCategoryByIdResponse handle(DeleteCategoryByIdRequest requestData) {
        Category category = categoryRepository.findById(requestData.getId()).get();
        List<Product> productList = category.getProductList();
        for (Product product : productList) {
            imageRepository.deleteAll(product.getImageList());
        }
        productRepository.deleteAll(productList);
        if (category.getCloudinaryId() != null) {
            cloudinaryClient.deleteImage(new DeleteImageClientRequest(List.of(category.getCloudinaryId())));
        }
        categoryRepository.deleteById(requestData.getId());
        return new DeleteCategoryByIdResponse(requestData.getId());
    }
}
