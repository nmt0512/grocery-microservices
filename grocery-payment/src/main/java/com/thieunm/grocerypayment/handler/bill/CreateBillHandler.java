package com.thieunm.grocerypayment.handler.bill;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerypayment.client.cart.ICartClient;
import com.thieunm.grocerypayment.client.cart.dto.request.DeleteAndGetCartByIdListClientRequest;
import com.thieunm.grocerypayment.client.cart.dto.response.DeleteAndGetCartByIdListClientResponse;
import com.thieunm.grocerypayment.client.cart.dto.response.InternalCartResponse;
import com.thieunm.grocerypayment.client.product.IProductClient;
import com.thieunm.grocerypayment.client.product.dto.request.DeductProductRequest;
import com.thieunm.grocerypayment.client.product.dto.request.DeductQuantityListProductClientRequest;
import com.thieunm.grocerypayment.dto.request.bill.CreateBillRequest;
import com.thieunm.grocerypayment.dto.response.bill.BillItemResponse;
import com.thieunm.grocerypayment.dto.response.bill.BillResponse;
import com.thieunm.grocerypayment.dto.response.bill.CreateBillResponse;
import com.thieunm.grocerypayment.entity.Bill;
import com.thieunm.grocerypayment.entity.BillItem;
import com.thieunm.grocerypayment.enums.BillStatus;
import com.thieunm.grocerypayment.enums.PickUpDateEnum;
import com.thieunm.grocerypayment.repository.BillRepository;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateBillHandler extends CommandHandler<CreateBillRequest, CreateBillResponse> {

    private final BillRepository billRepository;
    private final ICartClient cartClient;
    private final IProductClient productClient;

    @Override
    @Transactional
    public CreateBillResponse handle(CreateBillRequest requestData) {
        // TODO try catch to rollback

        // DELETE cart item
        DeleteAndGetCartByIdListClientRequest cartClientRequest = new DeleteAndGetCartByIdListClientRequest(requestData.getCartIdList());
        DeleteAndGetCartByIdListClientResponse cartClientResponse = cartClient.deleteAndGetCartByIdList(cartClientRequest);

        // DEDUCT product quantity
        List<DeductProductRequest> deductProductRequestList = cartClientResponse.getInternalCartResponseList()
                .stream()
                .map(internalCartResponse -> DeductProductRequest
                        .builder()
                        .productId(internalCartResponse.getProductId())
                        .deductingQuantity(internalCartResponse.getQuantity())
                        .build())
                .toList();
        DeductQuantityListProductClientRequest productClientRequest = new DeductQuantityListProductClientRequest(deductProductRequestList);
        productClient.deductQuantityListProduct(productClientRequest);

        // PREPARE bill
        int billTotalPrice = cartClientResponse.getInternalCartResponseList()
                .stream()
                .mapToInt(InternalCartResponse::getTotalPrice)
                .sum();

        Bill bill = Bill.builder()
                .customerId(JsonWebTokenUtil.getUserId(requestData.getAccessToken()))
                .status(BillStatus.PAID)
                .totalPrice(billTotalPrice)
                .pickUpTime(getPickUpTime(requestData))
                .build();
        List<BillItem> billItemList = cartClientResponse.getInternalCartResponseList()
                .stream()
                .map(internalCartResponse -> BillItem.builder()
                        .productId(internalCartResponse.getProductId())
                        .quantity(internalCartResponse.getQuantity())
                        .price(internalCartResponse.getTotalPrice())
                        .bill(bill)
                        .build())
                .toList();
        bill.setBillItemList(billItemList);

        // SAVE bill
        Bill savedBill = billRepository.save(bill);

        // CREATE bill response
        List<BillItemResponse> billItemResponseList = Mapper.mapList(savedBill.getBillItemList(), BillItemResponse.class);
        BillResponse billResponse = Mapper.map(savedBill, BillResponse.class);
        billResponse.setBillItemResponseList(billItemResponseList);
        return new CreateBillResponse(billResponse);
    }

    private LocalDateTime getPickUpTime(CreateBillRequest requestData) {
        int arrivalDay = Arrays.stream(PickUpDateEnum.values())
                .filter(pickUpDateEnum -> pickUpDateEnum.getDescription().equals(requestData.getPickUpDate()))
                .mapToInt(PickUpDateEnum::getArrivalDay)
                .findFirst()
                .getAsInt();
        LocalDate pickUpDate = LocalDate.now().plusDays(arrivalDay);

        int indexOfColon = requestData.getPickUpTime().indexOf(":");
        int pickUpHour = Integer.parseInt(requestData.getPickUpTime().substring(0, indexOfColon));
        int pickUpMinute = Integer.parseInt(requestData.getPickUpTime().substring(indexOfColon + 1));
        LocalTime pickUpTime = LocalTime.of(pickUpHour, pickUpMinute);

        return LocalDateTime.of(pickUpDate, pickUpTime);
    }
}
