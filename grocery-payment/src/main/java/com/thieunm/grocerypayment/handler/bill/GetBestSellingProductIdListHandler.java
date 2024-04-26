package com.thieunm.grocerypayment.handler.bill;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerypayment.dto.request.bill.GetBestSellingProductIdListRequest;
import com.thieunm.grocerypayment.dto.response.bill.GetBestSellingProductIdListResponse;
import com.thieunm.grocerypayment.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBestSellingProductIdListHandler extends QueryHandler<GetBestSellingProductIdListRequest, GetBestSellingProductIdListResponse> {

    private final BillRepository billRepository;

    @Override
    public GetBestSellingProductIdListResponse handle(GetBestSellingProductIdListRequest requestData) {
        List<Integer> productIdList = billRepository.getBestSellingProductIdList(
                requestData.getRecentDays(),
                requestData.getSize()
        );
        return new GetBestSellingProductIdListResponse(productIdList);
    }
}
