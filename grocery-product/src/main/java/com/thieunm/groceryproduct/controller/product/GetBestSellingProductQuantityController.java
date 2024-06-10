package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.GetBestSellingProductQuantityRequest;
import com.thieunm.groceryproduct.dto.response.product.GetBestSellingProductQuantityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class GetBestSellingProductQuantityController extends BaseController {

    @GetMapping("/bestSellingQuantity")
    ResponseEntity<BaseResponse<GetBestSellingProductQuantityResponse>> getBestSellingProductQuantity() {
        return execute(new GetBestSellingProductQuantityRequest(), GetBestSellingProductQuantityResponse.class);
    }
}
