package com.thieunm.grocerypayment.controller.bill;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.bill.GetRecommendedProductIdListRequest;
import com.thieunm.grocerypayment.dto.response.bill.GetRecommendedProductIdListResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/bill")
public class GetRecommendedProductIdListController extends BaseController {

    @GetMapping("/recommendedProduct")
    ResponseEntity<BaseResponse<GetRecommendedProductIdListResponse>> getRecommendedProductIdList(
            @RequestParam("recentDays") int recentDays,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestParam("size") int size
    ) {
        GetRecommendedProductIdListRequest request = GetRecommendedProductIdListRequest.builder()
                .recentDays(recentDays)
                .size(size)
                .accessToken(token.substring(7))
                .build();
        return execute(request, GetRecommendedProductIdListResponse.class);
    }
}
