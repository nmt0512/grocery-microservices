package com.thieunm.groceryproduct.handler.product;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryproduct.dto.request.product.GetSimilarProductListByProductIdRequest;
import com.thieunm.groceryproduct.dto.response.product.GetSimilarProductListByProductIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSimilarProductListByProductIdHandler extends QueryHandler<GetSimilarProductListByProductIdRequest, GetSimilarProductListByProductIdResponse> {

    @Override
    public GetSimilarProductListByProductIdResponse handle(GetSimilarProductListByProductIdRequest requestData) {
        return null;
    }
}
