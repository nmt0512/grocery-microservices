package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.GetNumberOfProductRequest;
import com.thieunm.groceryproduct.dto.response.product.GetNumberOfProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class GetNumberOfProductController extends BaseController {

    @GetMapping("/numberOfProduct")
    ResponseEntity<BaseResponse<GetNumberOfProductResponse>> getNumberOfProduct() {
        return execute(new GetNumberOfProductRequest(), GetNumberOfProductResponse.class);
    }
}
