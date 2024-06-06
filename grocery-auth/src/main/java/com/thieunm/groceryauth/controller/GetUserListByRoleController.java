package com.thieunm.groceryauth.controller;

import com.thieunm.groceryauth.dto.request.GetUserListByRoleRequest;
import com.thieunm.groceryauth.dto.response.GetUserListByRoleResponse;
import com.thieunm.groceryauth.enums.RealmRoles;
import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class GetUserListByRoleController extends BaseController {

    @GetMapping("/admin/user")
    ResponseEntity<BaseResponse<GetUserListByRoleResponse>> getUserListByRole(
            @RequestParam("role") RealmRoles role
    ) {
        return execute(new GetUserListByRoleRequest(role), GetUserListByRoleResponse.class);
    }
}
