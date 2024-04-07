package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.GetAvailableProductListRequest;
import com.thieunm.groceryproduct.dto.response.product.GetAvailableProductListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class GetAvailableProductListController extends BaseController {

    @PostMapping("/available")
    ResponseEntity<BaseResponse<GetAvailableProductListResponse>> getAvailableProductList(@RequestBody GetAvailableProductListRequest request) {
        return execute(request, GetAvailableProductListResponse.class);
    }
}
