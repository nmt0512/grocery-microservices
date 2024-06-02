package com.thieunm.groceryproduct.controller.category;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.category.CreateCategoryRequest;
import com.thieunm.groceryproduct.dto.response.category.CreateCategoryResponse;
import com.thieunm.groceryutils.JsonMapperUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/category")
public class CreateCategoryController extends BaseController {

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<BaseResponse<CreateCategoryResponse>> createCategory(
            @RequestParam("file") MultipartFile file,
            @RequestParam("category") String categoryJson
    ) {
        CreateCategoryRequest request = JsonMapperUtil.parseJsonToObject(categoryJson, CreateCategoryRequest.class);
        request.setMultipartFile(file);
        return execute(request, CreateCategoryResponse.class);
    }
}
