package com.thieunm.grocerypayment.controller.bill;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.bill.CreateBillRequest;
import com.thieunm.grocerypayment.dto.response.bill.CreateBillResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bill")
public class CreateBillController extends BaseController {

    @PostMapping
    ResponseEntity<BaseResponse<CreateBillResponse>> createBill(
            @RequestBody @Valid CreateBillRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        request.setAccessToken(token.substring(7));
        return execute(request, CreateBillResponse.class);
    }
}
