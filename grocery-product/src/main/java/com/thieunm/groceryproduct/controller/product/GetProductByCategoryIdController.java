package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.GetProductByCategoryIdRequest;
import com.thieunm.groceryproduct.dto.response.product.GetProductByCategoryIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class GetProductByCategoryIdController extends BaseController {

    @GetMapping("/category")
    ResponseEntity<BaseResponse<GetProductByCategoryIdResponse>> getProductByCategoryId(
            @RequestParam("categoryId") int categoryId
    ) {
        return execute(new GetProductByCategoryIdRequest(categoryId), GetProductByCategoryIdResponse.class);
    }
}
