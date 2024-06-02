package com.thieunm.groceryproduct.controller.category;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.category.DeleteCategoryByIdRequest;
import com.thieunm.groceryproduct.dto.response.category.DeleteCategoryByIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class DeleteCategoryByIdController extends BaseController {

    @DeleteMapping("/{id}")
    ResponseEntity<BaseResponse<DeleteCategoryByIdResponse>> deleteCategoryById(@PathVariable("id") int id) {
        return execute(new DeleteCategoryByIdRequest(id), DeleteCategoryByIdResponse.class);
    }
}
