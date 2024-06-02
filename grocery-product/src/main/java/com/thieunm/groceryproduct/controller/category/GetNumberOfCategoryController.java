package com.thieunm.groceryproduct.controller.category;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.category.GetNumberOfCategoryRequest;
import com.thieunm.groceryproduct.dto.response.category.GetNumberOfCategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class GetNumberOfCategoryController extends BaseController {

    @GetMapping("/numberOfCategory")
    ResponseEntity<BaseResponse<GetNumberOfCategoryResponse>> getNumberOfCategory() {
        return execute(new GetNumberOfCategoryRequest(), GetNumberOfCategoryResponse.class);
    }
}
