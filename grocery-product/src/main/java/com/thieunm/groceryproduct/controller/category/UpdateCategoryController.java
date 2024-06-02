package com.thieunm.groceryproduct.controller.category;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.category.UpdateCategoryRequest;
import com.thieunm.groceryproduct.dto.response.category.UpdateCategoryResponse;
import com.thieunm.groceryutils.JsonMapperUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/category")
public class UpdateCategoryController extends BaseController {

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<BaseResponse<UpdateCategoryResponse>> updateCategory(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("category") String categoryJson
    ) {
        UpdateCategoryRequest request = JsonMapperUtil.parseJsonToObject(categoryJson, UpdateCategoryRequest.class);
        if (file != null) {
            request.setMultipartFile(file);
        }
        return execute(request, UpdateCategoryResponse.class);
    }
}
