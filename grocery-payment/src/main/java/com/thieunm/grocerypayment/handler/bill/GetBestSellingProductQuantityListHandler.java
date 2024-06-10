package com.thieunm.grocerypayment.handler.bill;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerypayment.dto.request.bill.GetBestSellingProductQuantityListRequest;
import com.thieunm.grocerypayment.dto.response.bill.BestSellingProductQuantityResponse;
import com.thieunm.grocerypayment.dto.response.bill.GetBestSellingProductQuantityListResponse;
import com.thieunm.grocerypayment.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBestSellingProductQuantityListHandler extends QueryHandler<GetBestSellingProductQuantityListRequest, GetBestSellingProductQuantityListResponse> {

    private final StatisticRepository statisticRepository;

    @Override
    public GetBestSellingProductQuantityListResponse handle(GetBestSellingProductQuantityListRequest requestData) {
        List<BestSellingProductQuantityResponse> productQuantityResponseList = statisticRepository.getBestSellingProductQuantityList(
                requestData.getRecentDays(),
                requestData.getSize()
        );
        return new GetBestSellingProductQuantityListResponse(productQuantityResponseList);
    }
}
