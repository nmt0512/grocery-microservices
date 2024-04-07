package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.GetUserInfoRequest;
import com.thieunm.groceryauth.dto.response.GetUserInfoResponse;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class GetUserInfoController extends BaseController {

    @GetMapping("/userInfo")
    public ResponseEntity<BaseResponse<GetUserInfoResponse>> getUserInfo(@RequestHeader(name = "Authorization") String authorizationHeaderValue) {
        GetUserInfoRequest request = new GetUserInfoRequest();
        request.setAccessToken(authorizationHeaderValue.substring(7));
        return execute(request, GetUserInfoResponse.class);
    }
}
