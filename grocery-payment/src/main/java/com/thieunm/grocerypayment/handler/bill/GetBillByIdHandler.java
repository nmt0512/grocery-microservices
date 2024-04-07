package com.thieunm.grocerypayment.handler.bill;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerypayment.dto.request.bill.GetBillByIdRequest;
import com.thieunm.grocerypayment.dto.response.bill.GetBillByIdResponse;

public class GetBillByIdHandler extends QueryHandler<GetBillByIdRequest, GetBillByIdResponse> {

    @Override
    public GetBillByIdResponse handle(GetBillByIdRequest requestData) {
        return null;
    }
}
