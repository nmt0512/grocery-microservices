package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.CreateProductRequest;
import com.thieunm.groceryproduct.dto.response.product.CreateProductResponse;
import com.thieunm.groceryutils.JsonMapperUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class CreateProductController extends BaseController {

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<BaseResponse<CreateProductResponse>> createProduct(
            @RequestParam("files") MultipartFile[] fileList,
            @RequestParam("product") String productJson
    ) {
        CreateProductRequest request = JsonMapperUtil.parseJsonToObject(productJson, CreateProductRequest.class);
        request.setFileList(List.of(fileList));
        return execute(request, CreateProductResponse.class);
    }
}
