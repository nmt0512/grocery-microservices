package com.thieunm.grocerypayment.controller.statistic;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.statistic.GetYearlyStatisticRequest;
import com.thieunm.grocerypayment.dto.response.statistic.GetYearlyStatisticResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistic")
public class GetYearlyStatisticController extends BaseController {

    @GetMapping("/yearly")
    ResponseEntity<BaseResponse<GetYearlyStatisticResponse>> getYearlyStatistic() {
        return execute(new GetYearlyStatisticRequest(), GetYearlyStatisticResponse.class);
    }
}
