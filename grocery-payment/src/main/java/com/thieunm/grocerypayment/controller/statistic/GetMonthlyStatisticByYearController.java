package com.thieunm.grocerypayment.controller.statistic;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.statistic.GetMonthlyStatisticByYearRequest;
import com.thieunm.grocerypayment.dto.response.statistic.GetMonthlyStatisticByYearResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistic")
public class GetMonthlyStatisticByYearController extends BaseController {

    @GetMapping("/monthly/{year}")
    ResponseEntity<BaseResponse<GetMonthlyStatisticByYearResponse>> getMonthlyStatisticByYear(
            @PathVariable("year") int year
    ) {
        return execute(new GetMonthlyStatisticByYearRequest(year), GetMonthlyStatisticByYearResponse.class);
    }
}
