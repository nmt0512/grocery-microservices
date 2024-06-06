package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.RefreshTokenRequest;
import com.thieunm.groceryauth.dto.response.RefreshTokenResponse;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RefreshTokenController extends BaseController {

    @PostMapping("/refreshToken")
    ResponseEntity<BaseResponse<RefreshTokenResponse>> refreshToken(
            @RequestHeader("refresh_token") String refreshToken
    ) {
        return execute(new RefreshTokenRequest(refreshToken), RefreshTokenResponse.class);
    }
}
