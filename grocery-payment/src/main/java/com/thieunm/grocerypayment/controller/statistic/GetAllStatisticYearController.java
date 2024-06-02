package com.thieunm.grocerypayment.controller.statistic;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.dto.request.statistic.GetAllStatisticYearRequest;
import com.thieunm.grocerypayment.dto.response.statistic.GetAllStatisticYearResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistic")
public class GetAllStatisticYearController extends BaseController {

    @GetMapping("/year/all")
    ResponseEntity<BaseResponse<GetAllStatisticYearResponse>> getAllStatisticYear() {
        return execute(new GetAllStatisticYearRequest(), GetAllStatisticYearResponse.class);
    }
}
