package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.CreateUserRequest;
import com.thieunm.groceryauth.dto.response.CreateUserResponse;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class CreateUserController extends BaseController {

    @PostMapping("/admin/user")
    ResponseEntity<BaseResponse<CreateUserResponse>> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return execute(createUserRequest, CreateUserResponse.class);
    }
}
