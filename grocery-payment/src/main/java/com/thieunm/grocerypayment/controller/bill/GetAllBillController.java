package com.thieunm.grocerypayment.controller.bill;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.bill.GetAllBillRequest;
import com.thieunm.grocerypayment.dto.response.bill.GetAllBillResponse;
import com.thieunm.grocerypayment.enums.BillStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class GetAllBillController extends BaseController {

    @GetMapping("/all")
    ResponseEntity<BaseResponse<GetAllBillResponse>> getAllBill(
            @RequestParam(name = "status", required = false) List<BillStatus> billStatusList,
            @RequestParam(name = "page", required = false) Integer pageNumber,
            @RequestParam(name = "size", required = false) Integer pageSize,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        GetAllBillRequest request = GetAllBillRequest.builder()
                .accessToken(token.substring(7))
                .billStatusList(billStatusList)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
        return execute(request, GetAllBillResponse.class);
    }
}
