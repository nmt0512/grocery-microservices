package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.CustomerLoginRequest;
import com.thieunm.groceryauth.dto.response.CustomerLoginResponse;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class CustomerLoginController extends BaseController {

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<CustomerLoginResponse>> login(@RequestBody CustomerLoginRequest customerLoginRequest) {
        return execute(customerLoginRequest, CustomerLoginResponse.class);
    }
}
