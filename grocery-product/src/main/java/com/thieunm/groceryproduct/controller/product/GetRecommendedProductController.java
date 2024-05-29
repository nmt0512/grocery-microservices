package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.GetRecommendedProductRequest;
import com.thieunm.groceryproduct.dto.response.product.GetRecommendedProductResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class GetRecommendedProductController extends BaseController {

    @GetMapping("/recommended")
    ResponseEntity<BaseResponse<GetRecommendedProductResponse>> getRecommendedProduct(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        GetRecommendedProductRequest request = GetRecommendedProductRequest.builder()
                .accessToken(token)
                .build();
        return execute(request, GetRecommendedProductResponse.class);
    }
}
