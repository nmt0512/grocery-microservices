package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.SearchProductRequest;
import com.thieunm.groceryproduct.dto.response.product.SearchProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class SearchProductController extends BaseController {

    @GetMapping("/search")
    ResponseEntity<BaseResponse<SearchProductResponse>> searchProduct(@RequestParam("query") String query) {
        return execute(new SearchProductRequest(query), SearchProductResponse.class);
    }
}
