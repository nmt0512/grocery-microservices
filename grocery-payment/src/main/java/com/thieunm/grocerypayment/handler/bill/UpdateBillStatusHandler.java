package com.thieunm.grocerypayment.handler.bill;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerypayment.dto.request.bill.UpdateBillStatusRequest;
import com.thieunm.grocerypayment.dto.response.bill.BillResponse;
import com.thieunm.grocerypayment.dto.response.bill.UpdateBillStatusResponse;
import com.thieunm.grocerypayment.entity.Bill;
import com.thieunm.grocerypayment.repository.BillRepository;
import com.thieunm.grocerypayment.utils.BillUtil;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateBillStatusHandler extends CommandHandler<UpdateBillStatusRequest, UpdateBillStatusResponse> {

    private final BillRepository billRepository;
    private final BillUtil billUtil;

    @Override
    @Transactional
    public UpdateBillStatusResponse handle(UpdateBillStatusRequest requestData) {
        String staffId = JsonWebTokenUtil.getUserId(requestData.getAccessToken());
        Bill bill = billRepository.findById(requestData.getBillId()).get();
        bill.setStatus(requestData.getBillStatus());
        bill.setStaffId(staffId);
        bill = billRepository.save(bill);
        BillResponse billResponse = billUtil.mapBillToBillResponse(bill);
        return new UpdateBillStatusResponse(billResponse);
    }
}
