package com.thieunm.grocerypayment.handler.statistic;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerypayment.dto.request.statistic.GetAllStatisticYearRequest;
import com.thieunm.grocerypayment.dto.response.statistic.GetAllStatisticYearResponse;
import com.thieunm.grocerypayment.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetAllStatisticYearHandler extends QueryHandler<GetAllStatisticYearRequest, GetAllStatisticYearResponse> {

    private final StatisticRepository statisticRepository;

    @Override
    public GetAllStatisticYearResponse handle(GetAllStatisticYearRequest requestData) {
        List<Integer> yearList = statisticRepository.getAllStatisticYear()
                .stream()
                .filter(Objects::nonNull)
                .toList();
        return new GetAllStatisticYearResponse(yearList);
    }
}
