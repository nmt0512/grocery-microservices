package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.UpdateUserInfoRequest;
import com.thieunm.groceryauth.dto.response.UpdateUserInfoResponse;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UpdateUserInfoController extends BaseController {

    @PutMapping("/userInfo")
    ResponseEntity<BaseResponse<UpdateUserInfoResponse>> updateUserInfo(
            @RequestBody UpdateUserInfoRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        request.setAccessToken(token.substring(7));
        return execute(request, UpdateUserInfoResponse.class);
    }
}
