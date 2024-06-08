package com.thieunm.grocerypayment.handler.bill;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerybase.enums.NotifyType;
import com.thieunm.grocerypayment.dto.request.bill.UpdateBillStatusRequest;
import com.thieunm.grocerypayment.dto.response.bill.BillResponse;
import com.thieunm.grocerypayment.dto.response.bill.UpdateBillStatusResponse;
import com.thieunm.grocerypayment.entity.Bill;
import com.thieunm.grocerypayment.kafka.message.NotifyRequest;
import com.thieunm.grocerypayment.kafka.producer.NotifyProducer;
import com.thieunm.grocerypayment.repository.BillRepository;
import com.thieunm.grocerypayment.utils.BillUtil;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateBillStatusHandler extends CommandHandler<UpdateBillStatusRequest, UpdateBillStatusResponse> {

    private final BillRepository billRepository;
    private final BillUtil billUtil;

    private final NotifyProducer notifyProducer;

    @Override
    @Transactional
    public UpdateBillStatusResponse handle(UpdateBillStatusRequest requestData) {
        String staffId = JsonWebTokenUtil.getUserId(requestData.getAccessToken());
        Bill bill = billRepository.findById(requestData.getBillId()).get();
        bill.setStatus(requestData.getBillStatus());
        bill.setStaffId(staffId);
        bill = billRepository.save(bill);
        sendPushNotification(bill);
        BillResponse billResponse = billUtil.mapBillToBillResponse(bill);
        return new UpdateBillStatusResponse(billResponse);
    }

    private void sendPushNotification(Bill bill) {
        String title = bill.getStatus().getDescription() + " đơn hàng";
        String body = "Đơn hàng có ID [" + bill.getId() + "] " + bill.getStatus().getDescription().toLowerCase();
        String to = bill.getCustomerId();
        NotifyRequest notifyRequest = NotifyRequest.builder()
                .type(NotifyType.PUSH)
                .to(to)
                .title(title)
                .body(body)
                .build();
        notifyProducer.sendNotifyRequest(notifyRequest);
    }
}
