package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.StaffLoginRequest;
import com.thieunm.groceryauth.dto.response.StaffLoginResponse;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class StaffLoginController extends BaseController {

    @PostMapping("/staffLogin")
    ResponseEntity<BaseResponse<StaffLoginResponse>> staffLogin(@RequestBody StaffLoginRequest request) {
        return execute(request, StaffLoginResponse.class);
    }
}
