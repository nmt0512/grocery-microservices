package com.thieunm.grocerypayment.handler.bill;

import com.thieunm.grocerybase.constant.AccountRoles;
import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerypayment.dto.request.bill.GetAllBillRequest;
import com.thieunm.grocerypayment.dto.response.bill.BillResponse;
import com.thieunm.grocerypayment.dto.response.bill.GetAllBillResponse;
import com.thieunm.grocerypayment.entity.Bill;
import com.thieunm.grocerypayment.repository.BillRepository;
import com.thieunm.grocerypayment.utils.BillUtil;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllBillHandler extends QueryHandler<GetAllBillRequest, GetAllBillResponse> {

    private final BillRepository billRepository;
    private final BillUtil billUtil;

    @Override
    public GetAllBillResponse handle(GetAllBillRequest requestData) {
        String userId = JsonWebTokenUtil.getUserId(requestData.getAccessToken());
        boolean isCustomerRole = JsonWebTokenUtil.getRoleList(requestData.getAccessToken())
                .stream()
                .anyMatch(role -> role.equals(AccountRoles.CUSTOMER));
        List<Bill> billList;
        if (isCustomerRole) {
            Sort createdDateDescSort = Sort.by(Sort.Direction.DESC, "createdDate");
            billList = requestData.getBillStatusList() == null
                    ?
                    billRepository.findByCustomerId(userId, createdDateDescSort)
                    :
                    billRepository.findByCustomerIdAndStatusIn(userId, requestData.getBillStatusList(), createdDateDescSort);

            List<BillResponse> billResponseList = billUtil.mapBillListToBillResponseList(billList);
            return new GetAllBillResponse(billResponseList);
        } else {
            int pageIndex = requestData.getPageNumber() - 1;
            Pageable pageable = PageRequest.of(
                    pageIndex,
                    requestData.getPageSize(),
                    Sort.by("pickUpTime").ascending()
            );
            billList = billRepository.findByStatusIn(requestData.getBillStatusList(), pageable);
        }
        List<BillResponse> billResponseList = billUtil.mapBillListToBillResponseList(billList);
        return new GetAllBillResponse(billResponseList);
    }
}
