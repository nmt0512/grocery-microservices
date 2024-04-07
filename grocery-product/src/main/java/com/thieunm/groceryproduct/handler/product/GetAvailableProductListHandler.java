package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.dto.request.product.GetAvailableProductListRequest;
import com.thieunm.groceryproduct.dto.response.product.GetAvailableProductListResponse;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryproduct.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAvailableProductListHandler extends QueryHandler<GetAvailableProductListRequest, GetAvailableProductListResponse> {

    private final ProductRepository productRepository;
    private final ProductUtil productUtil;

    @Override
    public GetAvailableProductListResponse handle(GetAvailableProductListRequest requestData) {
        GetAvailableProductListResponse response = GetAvailableProductListResponse
                .builder()
                .isAvailable(true)
                .build();
        List<ProductResponse> unavailableProductResponseList = new ArrayList<>();
        requestData.getAvailableProductRequestList().forEach(availableProductRequest -> {
            Product product = productRepository.findById(availableProductRequest.getProductId()).get();
            if (product.getQuantity() < availableProductRequest.getQuantity()) {
                response.setAvailable(false);
                ProductResponse productResponse = productUtil.mapProductToProductResponse(product);
                unavailableProductResponseList.add(productResponse);
            }
        });
        if (!response.isAvailable()) {
            response.setUnavailableProductResponseList(unavailableProductResponseList);
        }
        return response;
    }
}
