package com.thieunm.grocerypayment.controller.vnpay;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.vnpay.AuthenticateVNPayOrderRequest;
import com.thieunm.grocerypayment.dto.response.vnpay.AuthenticateVNPayOrderResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vnpay")
public class AuthenticateVNPayOrderController extends BaseController {

    @GetMapping("/vnpay-payment")
    public ResponseEntity<BaseResponse<AuthenticateVNPayOrderResponse>> authenticateVNPayOrder(HttpServletRequest request) {
        return execute(new AuthenticateVNPayOrderRequest(request), AuthenticateVNPayOrderResponse.class);
    }
}
