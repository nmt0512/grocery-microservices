package com.thieunm.grocerypayment.controller.statistic;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.statistic.GetDailyStatisticByMonthRequest;
import com.thieunm.grocerypayment.dto.response.statistic.GetDailyStatisticByMonthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistic")
public class GetDailyStatisticByMonthController extends BaseController {

    @GetMapping("/daily")
    ResponseEntity<BaseResponse<GetDailyStatisticByMonthResponse>> getDailyStatisticByMonth(
            @RequestParam("month") String month
    ) {
        return execute(new GetDailyStatisticByMonthRequest(month), GetDailyStatisticByMonthResponse.class);
    }
}
