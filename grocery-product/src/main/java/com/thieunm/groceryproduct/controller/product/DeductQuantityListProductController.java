package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.DeductQuantityListProductRequest;
import com.thieunm.groceryproduct.dto.response.product.DeductQuantityListProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/product")
public class DeductQuantityListProductController extends BaseController {

    @PutMapping("/deductQuantity")
    ResponseEntity<BaseResponse<DeductQuantityListProductResponse>> deductQuantityListProduct(
            @RequestBody DeductQuantityListProductRequest request
    ) {
        return execute(request, DeductQuantityListProductResponse.class);
    }
}
