package com.thieunm.groceryproduct.controller.category;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.category.GetAllCategoryRequest;
import com.thieunm.groceryproduct.dto.response.category.GetAllCategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class GetAllCategoryController extends BaseController {

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<GetAllCategoryResponse>> getAllCategory() {
        GetAllCategoryRequest request = new GetAllCategoryRequest();
        return execute(request, GetAllCategoryResponse.class);
    }
}
