package com.thieunm.grocerypayment.controller.bill;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.bill.GetBestSellingProductQuantityListRequest;
import com.thieunm.grocerypayment.dto.response.bill.GetBestSellingProductQuantityListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/bill")
public class GetBestSellingProductQuantityListController extends BaseController {

    @GetMapping("/bestSellingProductQuantity")
    ResponseEntity<BaseResponse<GetBestSellingProductQuantityListResponse>> getBestSellingProductQuantityList(
            @RequestParam("recentDays") int recentDays,
            @RequestParam("size") int size
    ) {
        return execute(new GetBestSellingProductQuantityListRequest(recentDays, size), GetBestSellingProductQuantityListResponse.class);
    }
}
