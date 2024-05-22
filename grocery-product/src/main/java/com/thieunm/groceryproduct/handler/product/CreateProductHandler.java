package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryproduct.dto.request.product.CreateProductRequest;
import com.thieunm.groceryproduct.dto.response.product.CreateProductResponse;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.entity.Category;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.CategoryRepository;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductHandler extends CommandHandler<CreateProductRequest, CreateProductResponse> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductUtil productUtil;

    @Override
    public CreateProductResponse handle(CreateProductRequest requestData) {
        Product product = Mapper.map(requestData, Product.class);

        Category category = categoryRepository.findById(requestData.getCategoryId()).get();
        String productCode = productUtil.convertNameToCode(requestData.getName());
        product.setCategory(category);
        product.setCode(productCode);

        product = productRepository.save(product);

        productUtil.saveProductImages(product, requestData.getFileList());

        ProductResponse productResponse = Mapper.map(product, ProductResponse.class);
        return new CreateProductResponse(productResponse);
    }


}
