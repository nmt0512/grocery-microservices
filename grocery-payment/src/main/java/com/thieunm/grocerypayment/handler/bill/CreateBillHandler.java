package com.thieunm.grocerypayment.handler.bill;

import com.corundumstudio.socketio.SocketIOServer;
import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerypayment.client.cart.ICartClient;
import com.thieunm.grocerypayment.client.cart.dto.request.DeleteAndGetCartByIdListClientRequest;
import com.thieunm.grocerypayment.client.cart.dto.response.DeleteAndGetCartByIdListClientResponse;
import com.thieunm.grocerypayment.client.cart.dto.response.InternalCartResponse;
import com.thieunm.grocerypayment.client.product.IProductClient;
import com.thieunm.grocerypayment.client.product.dto.request.DeductQuantityListProductClientRequest;
import com.thieunm.grocerypayment.client.product.dto.request.DeductingProduct;
import com.thieunm.grocerypayment.client.product.dto.response.DeductQuantityListProductClientResponse;
import com.thieunm.grocerypayment.dto.request.bill.CreateBillRequest;
import com.thieunm.grocerypayment.dto.response.bill.BillItemResponse;
import com.thieunm.grocerypayment.dto.response.bill.BillResponse;
import com.thieunm.grocerypayment.dto.response.bill.CreateBillResponse;
import com.thieunm.grocerypayment.entity.Bill;
import com.thieunm.grocerypayment.entity.BillItem;
import com.thieunm.grocerypayment.enums.BillStatus;
import com.thieunm.grocerypayment.enums.PickUpDateEnum;
import com.thieunm.grocerypayment.exception.CartClientException;
import com.thieunm.grocerypayment.exception.ProductClientException;
import com.thieunm.grocerypayment.kafka.message.CartRollbackRequest;
import com.thieunm.grocerypayment.kafka.message.ProductRollbackRequest;
import com.thieunm.grocerypayment.kafka.producer.CartProducer;
import com.thieunm.grocerypayment.kafka.producer.ProductProducer;
import com.thieunm.grocerypayment.repository.BillRepository;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateBillHandler extends CommandHandler<CreateBillRequest, CreateBillResponse> {

    private final BillRepository billRepository;
    private final ICartClient cartClient;
    private final IProductClient productClient;

    private final ThreadPoolTaskScheduler taskScheduler;
    private final SocketIOServer socketIOServer;

    private final CartProducer cartProducer;
    private final ProductProducer productProducer;

    @Override
    @Transactional
    public CreateBillResponse handle(CreateBillRequest requestData) {

        DeleteAndGetCartByIdListClientResponse cartClientResponse = null;
        DeductQuantityListProductClientResponse productClientResponse = null;

        try {
            // DELETE cart item
            DeleteAndGetCartByIdListClientRequest cartClientRequest = new DeleteAndGetCartByIdListClientRequest(requestData.getCartIdList());
            cartClientResponse = cartClient.deleteAndGetCartByIdList(cartClientRequest);

            // DEDUCT product quantity
            List<DeductingProduct> deductingProductList = cartClientResponse.getInternalCartResponseList()
                    .stream()
                    .map(internalCartResponse -> DeductingProduct
                            .builder()
                            .productId(internalCartResponse.getProductId())
                            .deductingQuantity(internalCartResponse.getQuantity())
                            .build())
                    .toList();
            DeductQuantityListProductClientRequest productClientRequest = new DeductQuantityListProductClientRequest(deductingProductList);
            productClientResponse = productClient.deductQuantityListProduct(productClientRequest);

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

            // SCHEDULE SENDING SOCKET NOTIFICATION to Staff App
            sendSocketNotification(savedBill);

            // CREATE bill response
            List<BillItemResponse> billItemResponseList = Mapper.mapList(savedBill.getBillItemList(), BillItemResponse.class);
            BillResponse billResponse = Mapper.map(savedBill, BillResponse.class);
            billResponse.setBillItemResponseList(billItemResponseList);
            return new CreateBillResponse(billResponse);
        } catch (CartClientException cartClientException) {
            return null;
        } catch (ProductClientException productClientException) {
            cartProducer.sendRollbackRequestToCart(CartRollbackRequest.builder()
                    .internalCartResponseList(Objects
                            .requireNonNull(cartClientResponse)
                            .getInternalCartResponseList())
                    .build());
        } catch (Exception exception) {
            cartProducer.sendRollbackRequestToCart(CartRollbackRequest.builder()
                    .internalCartResponseList(Objects
                            .requireNonNull(cartClientResponse)
                            .getInternalCartResponseList())
                    .build());
            productProducer.sendRollbackRequestToProduct(ProductRollbackRequest.builder()
                    .deductingProductList(Objects
                            .requireNonNull(productClientResponse)
                            .getDeductingProductList())
                    .build());
        }
        return null;
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

    private void sendSocketNotification(Bill bill) {
        boolean isPickUpTimeToday = bill.getPickUpTime().toLocalDate().equals(LocalDate.now());
        if (isPickUpTimeToday) {
            socketIOServer.getBroadcastOperations().sendEvent("todayNewBill", bill.getId());
        }
        taskScheduler.schedule(
                () -> socketIOServer.getBroadcastOperations().sendEvent("incomingPickUpBill", bill.getId()),
                bill.getPickUpTime().minusMinutes(90).atZone(ZoneId.systemDefault()).toInstant()
        );
    }
}
