package com.thieunm.grocerypayment.controller.bill;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.bill.GetBestSellingProductIdListRequest;
import com.thieunm.grocerypayment.dto.response.bill.GetBestSellingProductIdListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/bill")
public class GetBestSellingProductIdListController extends BaseController {

    @GetMapping("/bestSellingProduct")
    ResponseEntity<BaseResponse<GetBestSellingProductIdListResponse>> getBestSellingProductIdList(
            @RequestParam("recentDays") int recentDays,
            @RequestParam("size") int size
    ) {
        return execute(new GetBestSellingProductIdListRequest(recentDays, size), GetBestSellingProductIdListResponse.class);
    }
}
