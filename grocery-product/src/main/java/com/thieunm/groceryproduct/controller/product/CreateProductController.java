package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.CreateProductRequest;
import com.thieunm.groceryproduct.dto.response.product.CreateProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/product")
public class CreateProductController extends BaseController {

    @PostMapping("/create")
    ResponseEntity<BaseResponse<CreateProductResponse>> createProduct(@RequestBody CreateProductRequest request) {
        return execute(request, CreateProductResponse.class);
    }
}
