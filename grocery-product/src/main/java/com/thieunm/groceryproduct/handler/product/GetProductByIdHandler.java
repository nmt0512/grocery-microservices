package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.dto.request.product.GetProductByIdRequest;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByIdHandler extends QueryHandler<GetProductByIdRequest, ProductResponse> {

    private final ProductRepository productRepository;
    private final ProductUtil productUtil;

    @Override
    public ProductResponse handle(GetProductByIdRequest requestData) {
        Product product = productRepository.findById(requestData.getId()).get();
        return productUtil.mapProductToProductResponse(product);
    }
}
