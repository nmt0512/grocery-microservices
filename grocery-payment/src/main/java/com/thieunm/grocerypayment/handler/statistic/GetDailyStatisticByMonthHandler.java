package com.thieunm.grocerypayment.handler.statistic;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerypayment.dto.request.statistic.GetDailyStatisticByMonthRequest;
import com.thieunm.grocerypayment.dto.response.statistic.GetDailyStatisticByMonthResponse;
import com.thieunm.grocerypayment.dto.response.statistic.StatisticResponse;
import com.thieunm.grocerypayment.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDailyStatisticByMonthHandler extends QueryHandler<GetDailyStatisticByMonthRequest, GetDailyStatisticByMonthResponse> {

    private final StatisticRepository statisticRepository;

    @Override
    public GetDailyStatisticByMonthResponse handle(GetDailyStatisticByMonthRequest requestData) {
        List<StatisticResponse> statisticResponseList = statisticRepository.getDailyStatisticByMonth(requestData.getMonth());
        return new GetDailyStatisticByMonthResponse(statisticResponseList);
    }
}
