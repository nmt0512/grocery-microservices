package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.GetProductByIdRequest;
import com.thieunm.groceryproduct.dto.response.product.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class GetProductByIdController extends BaseController {

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse<ProductResponse>> getProductById(@PathVariable("id") Integer id) {
        return execute(new GetProductByIdRequest(id), ProductResponse.class);
    }
}
