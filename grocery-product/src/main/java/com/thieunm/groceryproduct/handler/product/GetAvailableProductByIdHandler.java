package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.dto.request.product.GetAvailableProductByIdRequest;
import com.thieunm.groceryproduct.dto.response.product.GetAvailableProductByIdResponse;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAvailableProductByIdHandler extends QueryHandler<GetAvailableProductByIdRequest, GetAvailableProductByIdResponse> {

    private final ProductRepository repository;

    @Override
    public GetAvailableProductByIdResponse handle(GetAvailableProductByIdRequest requestData) {
        Product product = repository
                .findById(requestData.getProductId())
                .get();
        GetAvailableProductByIdResponse response = new GetAvailableProductByIdResponse();
        if (product.getQuantity() >= requestData.getQuantity()) {
            ProductResponse productResponse = Mapper.map(product, ProductResponse.class);
            response.setAvailable(true);
            response.setProductResponse(productResponse);
        } else {
            response.setAvailable(false);
        }
        return response;
    }
}
