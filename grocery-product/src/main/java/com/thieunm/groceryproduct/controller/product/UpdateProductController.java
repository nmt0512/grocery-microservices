package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.UpdateProductRequest;
import com.thieunm.groceryproduct.dto.response.product.UpdateProductResponse;
import com.thieunm.groceryutils.JsonMapperUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class UpdateProductController extends BaseController {

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<BaseResponse<UpdateProductResponse>> updateProduct(
            @RequestParam(name = "files", required = false) MultipartFile[] fileList,
            @RequestParam("product") String productJson
    ) {
        UpdateProductRequest request = JsonMapperUtil.parseJsonToObject(productJson, UpdateProductRequest.class);
        if (fileList != null) {
            request.setFileList(List.of(fileList));
        }
        return execute(request, UpdateProductResponse.class);
    }
}
