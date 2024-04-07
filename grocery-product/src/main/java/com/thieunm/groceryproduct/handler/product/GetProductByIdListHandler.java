package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.dto.request.product.GetProductByIdListRequest;
import com.thieunm.groceryproduct.dto.response.product.GetProductByIdListResponse;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductByIdListHandler extends QueryHandler<GetProductByIdListRequest, GetProductByIdListResponse> {

    private final ProductRepository productRepository;
    private final ProductUtil productUtil;

    @Override
    public GetProductByIdListResponse handle(GetProductByIdListRequest requestData) {
        List<Product> productList = productRepository.findAllById(requestData.getProductIdList());
        List<ProductResponse> productResponseList = productList
                .stream()
                .map(productUtil::mapProductToProductResponse)
                .toList();
        return new GetProductByIdListResponse(productResponseList);
    }
}
