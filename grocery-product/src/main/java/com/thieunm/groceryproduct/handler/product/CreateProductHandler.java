package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryproduct.dto.request.product.CreateProductRequest;
import com.thieunm.groceryproduct.dto.response.product.CreateProductResponse;
import com.thieunm.groceryproduct.entity.Category;
import com.thieunm.groceryproduct.entity.Image;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.CategoryRepository;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateProductHandler extends CommandHandler<CreateProductRequest, CreateProductResponse> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductUtil productUtil;

    @Override
    public CreateProductResponse handle(CreateProductRequest requestData) {
        Category category = categoryRepository.findById(requestData.getCategoryId()).get();
        String productCode = productUtil.convertNameToCode(requestData.getName());

        Product product = Mapper.map(requestData, Product.class);
        product.setCategory(category);
        product.setCode(productCode);

        List<Image> imageList = new ArrayList<>();
        for (String imageUrl : requestData.getImageUrlList()) {
            Image image = Image.builder()
                    .product(product)
                    .url(imageUrl)
                    .resizedUrl(productUtil.getResizedImageUrl(imageUrl))
                    .build();
            imageList.add(image);
        }
        product.setImageList(imageList);

        product = productRepository.save(product);

        CreateProductResponse response = Mapper.map(product, CreateProductResponse.class);
        response.setCategoryId(product.getCategory().getId());

        return response;
    }
}
