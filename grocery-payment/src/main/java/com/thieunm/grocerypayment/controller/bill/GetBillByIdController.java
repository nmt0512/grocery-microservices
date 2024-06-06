package com.thieunm.grocerypayment.controller.bill;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.bill.GetBillByIdRequest;
import com.thieunm.grocerypayment.dto.response.bill.GetBillByIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bill")
public class GetBillByIdController extends BaseController {

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse<GetBillByIdResponse>> getBillById(@PathVariable("id") int id) {
        return execute(new GetBillByIdRequest(id), GetBillByIdResponse.class);
    }
}
