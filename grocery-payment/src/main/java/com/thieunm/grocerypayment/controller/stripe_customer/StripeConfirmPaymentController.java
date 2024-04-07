package com.thieunm.grocerypayment.controller.stripe_customer;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.stripe_customer.StripeConfirmPaymentRequest;
import com.thieunm.grocerypayment.dto.response.stripe_customer.StripeConfirmPaymentResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class StripeConfirmPaymentController extends BaseController {

    @PostMapping("/stripe")
    ResponseEntity<BaseResponse<StripeConfirmPaymentResponse>> stripePayment(
            @RequestBody StripeConfirmPaymentRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        request.setAccessToken(token.substring(7));
        return execute(request, StripeConfirmPaymentResponse.class);
    }
}
