package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.GetSimilarProductListByIdRequest;
import com.thieunm.groceryproduct.dto.response.product.GetSimilarProductListByIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class GetSimilarProductListByIdController extends BaseController {

    @GetMapping("/similar")
    ResponseEntity<BaseResponse<GetSimilarProductListByIdResponse>> getSimilarProductListById(@RequestParam("id") int id) {
        return execute(new GetSimilarProductListByIdRequest(id), GetSimilarProductListByIdResponse.class);
    }
}
