package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.GetAvailableProductByIdRequest;
import com.thieunm.groceryproduct.dto.response.product.GetAvailableProductByIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/product")
public class GetAvailableProductByIdController extends BaseController {

    @GetMapping("/available")
    ResponseEntity<BaseResponse<GetAvailableProductByIdResponse>> getAvailableProductById(
            @RequestParam("productId") Integer productId,
            @RequestParam("quantity") Integer quantity
    ) {
        return execute(new GetAvailableProductByIdRequest(productId, quantity), GetAvailableProductByIdResponse.class);
    }
}
