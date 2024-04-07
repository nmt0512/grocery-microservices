package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.ChangePasswordRequest;
import com.thieunm.groceryauth.dto.response.ChangePasswordResponse;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ChangePasswordController extends BaseController {

    @PutMapping("/password")
    ResponseEntity<BaseResponse<ChangePasswordResponse>> changePassword(
            @RequestBody ChangePasswordRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        request.setAccessToken(token.substring(7));
        return execute(request, ChangePasswordResponse.class);
    }
}
