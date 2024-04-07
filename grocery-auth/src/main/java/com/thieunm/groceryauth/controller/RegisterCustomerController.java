package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.RegisterCustomerRequest;
import com.thieunm.groceryauth.dto.response.RegisterCustomerResponse;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegisterCustomerController extends BaseController {

    @PostMapping("/registerCustomer")
    public ResponseEntity<BaseResponse<RegisterCustomerResponse>> registerCustomer(@RequestBody RegisterCustomerRequest request) {
        return execute(request, RegisterCustomerResponse.class);
    }
}
