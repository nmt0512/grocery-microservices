package com.thieunm.grocerycart.controller;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerycart.dto.request.AddToCartRequest;
import com.thieunm.grocerycart.dto.response.AddToCartResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class AddToCartController extends BaseController {

    @PostMapping("/add")
    ResponseEntity<BaseResponse<AddToCartResponse>> addToCart(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody AddToCartRequest request
    ) {
        request.setAccessToken(token.substring(7));
        return execute(request, AddToCartResponse.class);
    }
}
