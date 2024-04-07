package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.GetProductByIdListRequest;
import com.thieunm.groceryproduct.dto.response.product.GetProductByIdListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/internal/product")
public class GetProductByIdListController extends BaseController {

    @GetMapping
    ResponseEntity<BaseResponse<GetProductByIdListResponse>> getProductByIdList(
            @RequestParam("idList") List<Integer> productIdList
    ) {
        return execute(new GetProductByIdListRequest(productIdList), GetProductByIdListResponse.class);
    }
}
