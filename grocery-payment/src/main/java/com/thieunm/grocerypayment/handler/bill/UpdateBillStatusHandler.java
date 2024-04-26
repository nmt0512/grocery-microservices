package com.thieunm.grocerypayment.handler.bill;

import com.google.firebase.messaging.*;
import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerypayment.dto.request.bill.UpdateBillStatusRequest;
import com.thieunm.grocerypayment.dto.response.bill.BillResponse;
import com.thieunm.grocerypayment.dto.response.bill.UpdateBillStatusResponse;
import com.thieunm.grocerypayment.entity.Bill;
import com.thieunm.grocerypayment.entity.CustomerDevice;
import com.thieunm.grocerypayment.repository.BillRepository;
import com.thieunm.grocerypayment.repository.CustomerDeviceRepository;
import com.thieunm.grocerypayment.utils.BillUtil;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateBillStatusHandler extends CommandHandler<UpdateBillStatusRequest, UpdateBillStatusResponse> {

    private static final Logger log = LoggerFactory.getLogger(UpdateBillStatusHandler.class);
    private final BillRepository billRepository;
    private final BillUtil billUtil;

    private final CustomerDeviceRepository customerDeviceRepository;
    private final FirebaseMessaging firebaseMessaging;

    @Override
    @Transactional
    public UpdateBillStatusResponse handle(UpdateBillStatusRequest requestData) {
        String staffId = JsonWebTokenUtil.getUserId(requestData.getAccessToken());
        Bill bill = billRepository.findById(requestData.getBillId()).get();
        bill.setStatus(requestData.getBillStatus());
        bill.setStaffId(staffId);
        bill = billRepository.save(bill);
        pushNotificationToCustomer(bill);
        BillResponse billResponse = billUtil.mapBillToBillResponse(bill);
        return new UpdateBillStatusResponse(billResponse);
    }

    private void pushNotificationToCustomer(Bill bill) {
        List<CustomerDevice> customerDeviceList = customerDeviceRepository.findByCustomerId(bill.getCustomerId());
        List<String> deviceTokenList = customerDeviceList
                .stream()
                .map(CustomerDevice::getDeviceToken)
                .toList();
        MulticastMessage multicastMessage = MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(bill.getStatus().getDescription() + " đơn hàng")
                        .setBody("Đơn hàng có ID [" + bill.getId() + "] " + bill.getStatus().getDescription().toLowerCase())
                        .build())
                .addAllTokens(deviceTokenList)
                .build();
        try {
            BatchResponse batchResponse = firebaseMessaging.sendEachForMulticast(multicastMessage);
            log.info(String.valueOf(batchResponse.getSuccessCount()));
        } catch (FirebaseMessagingException exception) {
            exception.printStackTrace();
        }
    }
}
