package com.thieunm.grocerycart.controller;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerycart.dto.request.GetAllItemInCartRequest;
import com.thieunm.grocerycart.dto.response.GetAllItemInCartResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class GetAllItemInCartController extends BaseController {

    @GetMapping("/all")
    ResponseEntity<BaseResponse<GetAllItemInCartResponse>> getAllItemInCart(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        GetAllItemInCartRequest request = new GetAllItemInCartRequest();
        request.setAccessToken(token.substring(7));
        return execute(request, GetAllItemInCartResponse.class);
    }
}
