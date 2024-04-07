package com.thieunm.grocerycart.controller;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerycart.dto.request.UpdateCartQuantityRequest;
import com.thieunm.grocerycart.dto.response.UpdateCartQuantityResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class UpdateCartQuantityController extends BaseController {

    @PutMapping("/quantity")
    ResponseEntity<BaseResponse<UpdateCartQuantityResponse>> updateCartQuantity(
            @RequestBody @Valid UpdateCartQuantityRequest request
    ) {
        return execute(request, UpdateCartQuantityResponse.class);
    }
}
