package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.UpdateUserRequest;
import com.thieunm.groceryauth.dto.response.UpdateUserResponse;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UpdateUserController extends BaseController {

    @PutMapping("/admin/user")
    ResponseEntity<BaseResponse<UpdateUserResponse>> updateUser(@RequestBody UpdateUserRequest request) {
        return execute(request, UpdateUserResponse.class);
    }
}
