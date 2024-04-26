package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.GetBestSellingProductRequest;
import com.thieunm.groceryproduct.dto.response.product.GetBestSellingProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class GetBestSellingProductController extends BaseController {

    @GetMapping("/bestSelling")
    ResponseEntity<BaseResponse<GetBestSellingProductResponse>> getBestSellingProduct() {
        return execute(new GetBestSellingProductRequest(), GetBestSellingProductResponse.class);
    }
}
