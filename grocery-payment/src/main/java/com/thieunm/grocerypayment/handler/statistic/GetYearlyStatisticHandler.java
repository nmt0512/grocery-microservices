package com.thieunm.grocerypayment.handler.statistic;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerypayment.dto.request.statistic.GetYearlyStatisticRequest;
import com.thieunm.grocerypayment.dto.response.statistic.GetYearlyStatisticResponse;
import com.thieunm.grocerypayment.dto.response.statistic.StatisticResponse;
import com.thieunm.grocerypayment.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetYearlyStatisticHandler extends QueryHandler<GetYearlyStatisticRequest, GetYearlyStatisticResponse> {

    private final StatisticRepository statisticRepository;

    @Override
    public GetYearlyStatisticResponse handle(GetYearlyStatisticRequest requestData) {
        List<StatisticResponse> statisticResponseList = statisticRepository.getYearlyStatistic();
        return new GetYearlyStatisticResponse(statisticResponseList);
    }
}
