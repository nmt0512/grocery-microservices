package com.thieunm.grocerypayment.controller.vnpay;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.vnpay.CreateVNPayOrderRequest;
import com.thieunm.grocerypayment.dto.response.vnpay.CreateVNPayOrderResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@Slf4j
public class CreateVNPayOrderController extends BaseController {

    @PostMapping("/vnpay")
    public ResponseEntity<BaseResponse<CreateVNPayOrderResponse>> createVNPayOrder(
            @RequestParam("amount") int orderTotal,
            @RequestParam("orderInfo") String orderInfo,
            HttpServletRequest httpServletRequest
    ) {
        String urlReturn = httpServletRequest.getScheme() + "://"
                + httpServletRequest.getServerName() + ":"
                + httpServletRequest.getServerPort();
        CreateVNPayOrderRequest request = CreateVNPayOrderRequest.builder()
                .urlReturn(urlReturn)
                .orderInfo(orderInfo)
                .orderTotal(orderTotal)
                .build();
        return execute(request, CreateVNPayOrderResponse.class);
    }
}
