package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.dto.request.product.GetProductByCategoryIdRequest;
import com.thieunm.groceryproduct.dto.response.product.GetProductByCategoryIdResponse;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductByCategoryIdHandler extends QueryHandler<GetProductByCategoryIdRequest, GetProductByCategoryIdResponse> {

    private final ProductRepository productRepository;

    private final ProductUtil productUtil;

    @Override
    public GetProductByCategoryIdResponse handle(GetProductByCategoryIdRequest requestData) {
        List<Product> productList = productRepository.findByCategoryId(requestData.getCategoryId(), null);
        List<ProductResponse> productResponseList = productList
                .stream()
                .map(product -> {
                    ProductResponse productResponse = productUtil.mapProductToProductResponse(product);
                    productResponse.setCategoryId(requestData.getCategoryId());
                    return productResponse;
                })
                .toList();
        return new GetProductByCategoryIdResponse(productResponseList);
    }
}
