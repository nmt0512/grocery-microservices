package com.thieunm.grocerynotify.controller;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerynotify.dto.request.CreateCustomerDeviceRequest;
import com.thieunm.grocerynotify.dto.response.CreateCustomerDeviceResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
public class CreateCustomerDeviceController extends BaseController {

    @PostMapping("/device")
    ResponseEntity<BaseResponse<CreateCustomerDeviceResponse>> createCustomerDevice(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody CreateCustomerDeviceRequest request
    ) {
        request.setAccessToken(token.substring(7));
        return execute(request, CreateCustomerDeviceResponse.class);
    }
}
