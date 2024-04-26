package com.thieunm.grocerypayment.controller.customer_device;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.customer_device.CreateCustomerDeviceRequest;
import com.thieunm.grocerypayment.dto.response.customer_device.CreateCustomerDeviceResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/device")
public class CreateCustomerDeviceController extends BaseController {

    @PostMapping
    ResponseEntity<BaseResponse<CreateCustomerDeviceResponse>> createCustomerDevice(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody CreateCustomerDeviceRequest request
    ) {
        request.setAccessToken(token.substring(7));
        return execute(request, CreateCustomerDeviceResponse.class);
    }
}
