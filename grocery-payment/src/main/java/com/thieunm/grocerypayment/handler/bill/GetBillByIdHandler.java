package com.thieunm.grocerypayment.handler.bill;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerypayment.dto.request.bill.GetBillByIdRequest;
import com.thieunm.grocerypayment.dto.response.bill.BillResponse;
import com.thieunm.grocerypayment.dto.response.bill.GetBillByIdResponse;
import com.thieunm.grocerypayment.entity.Bill;
import com.thieunm.grocerypayment.repository.BillRepository;
import com.thieunm.grocerypayment.utils.BillUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetBillByIdHandler extends QueryHandler<GetBillByIdRequest, GetBillByIdResponse> {

    private final BillRepository billRepository;
    private final BillUtil billUtil;

    @Override
    public GetBillByIdResponse handle(GetBillByIdRequest requestData) {
        Bill bill = billRepository.findById(requestData.getId()).orElseThrow(NoSuchElementException::new);
        BillResponse billResponse = billUtil.mapBillListToBillResponseList(List.of(bill)).get(0);
        return new GetBillByIdResponse(billResponse);
    }
}
