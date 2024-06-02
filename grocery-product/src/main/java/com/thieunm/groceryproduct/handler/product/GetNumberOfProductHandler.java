package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.dto.request.product.GetNumberOfProductRequest;
import com.thieunm.groceryproduct.dto.response.product.GetNumberOfProductResponse;
import com.thieunm.groceryproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetNumberOfProductHandler extends QueryHandler<GetNumberOfProductRequest, GetNumberOfProductResponse> {

    private final ProductRepository productRepository;

    @Override
    public GetNumberOfProductResponse handle(GetNumberOfProductRequest requestData) {
        return new GetNumberOfProductResponse(productRepository.count());
    }
}
