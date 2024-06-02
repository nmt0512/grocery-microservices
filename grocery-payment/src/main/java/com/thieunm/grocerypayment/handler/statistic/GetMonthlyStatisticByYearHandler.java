package com.thieunm.grocerypayment.handler.statistic;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerypayment.dto.request.statistic.GetMonthlyStatisticByYearRequest;
import com.thieunm.grocerypayment.dto.response.statistic.GetMonthlyStatisticByYearResponse;
import com.thieunm.grocerypayment.dto.response.statistic.StatisticResponse;
import com.thieunm.grocerypayment.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMonthlyStatisticByYearHandler extends QueryHandler<GetMonthlyStatisticByYearRequest, GetMonthlyStatisticByYearResponse> {

    private final StatisticRepository statisticRepository;

    @Override
    public GetMonthlyStatisticByYearResponse handle(GetMonthlyStatisticByYearRequest requestData) {
        List<StatisticResponse> statisticResponseList = statisticRepository.getMonthlyStatisticByYear(requestData.getYear());
        return new GetMonthlyStatisticByYearResponse(statisticResponseList);
    }
}
