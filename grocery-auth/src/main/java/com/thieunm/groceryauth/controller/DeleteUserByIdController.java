package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.DeleteUserByIdRequest;
import com.thieunm.groceryauth.dto.response.DeleteUserByIdResponse;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class DeleteUserByIdController extends BaseController {

    @DeleteMapping("/admin/user/{id}")
    ResponseEntity<BaseResponse<DeleteUserByIdResponse>> deleteUserById(@PathVariable("id") String id) {
        return execute(new DeleteUserByIdRequest(id), DeleteUserByIdResponse.class);
    }
}
