package com.thieunm.grocerypayment.controller.bill;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.bill.UpdateBillStatusRequest;
import com.thieunm.grocerypayment.dto.response.bill.UpdateBillStatusResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff/bill")
public class UpdateBillStatusController extends BaseController {

    @PutMapping("/status")
    ResponseEntity<BaseResponse<UpdateBillStatusResponse>> updateBillStatus(
            @RequestBody UpdateBillStatusRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        request.setAccessToken(token.substring(7));
        return execute(request, UpdateBillStatusResponse.class);
    }
}
