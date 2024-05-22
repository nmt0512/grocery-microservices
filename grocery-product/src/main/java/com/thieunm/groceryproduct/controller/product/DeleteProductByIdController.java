package com.thieunm.groceryproduct.controller.product;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.dto.request.product.DeleteProductByIdRequest;
import com.thieunm.groceryproduct.dto.response.product.DeleteProductByIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class DeleteProductByIdController extends BaseController {

    @DeleteMapping("/{id}")
    ResponseEntity<BaseResponse<DeleteProductByIdResponse>> deleteProductById(@PathVariable("id") int id) {
        return execute(new DeleteProductByIdRequest(id), DeleteProductByIdResponse.class);
    }
}
