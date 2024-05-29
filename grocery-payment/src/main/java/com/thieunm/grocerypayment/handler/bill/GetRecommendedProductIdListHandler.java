package com.thieunm.grocerypayment.handler.bill;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerypayment.dto.request.bill.GetRecommendedProductIdListRequest;
import com.thieunm.grocerypayment.dto.response.bill.GetRecommendedProductIdListResponse;
import com.thieunm.grocerypayment.repository.BillRepository;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRecommendedProductIdListHandler extends QueryHandler<GetRecommendedProductIdListRequest, GetRecommendedProductIdListResponse> {

    private final BillRepository billRepository;

    @Override
    public GetRecommendedProductIdListResponse handle(GetRecommendedProductIdListRequest requestData) {
        String customerId = JsonWebTokenUtil.getUserId(requestData.getAccessToken());
        List<Integer> productIdList = billRepository.getRecommendedProductIdList(
                requestData.getRecentDays(),
                customerId,
                requestData.getSize()
        );
        return new GetRecommendedProductIdListResponse(productIdList);
    }
}
