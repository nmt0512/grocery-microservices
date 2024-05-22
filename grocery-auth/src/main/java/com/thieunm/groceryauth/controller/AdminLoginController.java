package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.AdminLoginRequest;
import com.thieunm.groceryauth.dto.response.AdminLoginResponse;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AdminLoginController extends BaseController {

    @PostMapping("/adminLogin")
    ResponseEntity<BaseResponse<AdminLoginResponse>> adminLogin(@RequestBody AdminLoginRequest request) {
        return execute(request, AdminLoginResponse.class);
    }
}
