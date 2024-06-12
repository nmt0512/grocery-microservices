package com.thieunm.grocerypayment.utils;

import com.thieunm.grocerypayment.client.product.IProductClient;
import com.thieunm.grocerypayment.client.product.dto.request.GetProductByIdListClientRequest;
import com.thieunm.grocerypayment.client.product.dto.response.GetProductByIdListClientResponse;
import com.thieunm.grocerypayment.dto.response.bill.BillItemResponse;
import com.thieunm.grocerypayment.dto.response.bill.BillResponse;
import com.thieunm.grocerypayment.dto.response.bill.ProductResponse;
import com.thieunm.grocerypayment.entity.Bill;
import com.thieunm.grocerypayment.entity.BillItem;
import com.thieunm.groceryutils.DateTimeFormat;
import com.thieunm.groceryutils.DateTimeFormatUtil;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BillUtil {

    private final IProductClient productClient;

    public List<BillResponse> mapBillListToBillResponseList(List<Bill> billList) {
        List<Integer> productIdList = new ArrayList<>();
        billList.forEach(bill -> {
            List<Integer> productIdBillItemList = bill.getBillItemList()
                    .stream()
                    .map(BillItem::getProductId)
                    .toList();
            productIdList.addAll(productIdBillItemList);
        });
        List<Integer> distinctProductIdList = productIdList.stream()
                .distinct()
                .toList();
        GetProductByIdListClientRequest clientRequest = new GetProductByIdListClientRequest(distinctProductIdList);
        GetProductByIdListClientResponse clientResponse = productClient.getProductByIdList(clientRequest);

        return billList
                .stream()
                .map(bill -> {
                    BillResponse billResponse = Mapper.map(bill, BillResponse.class);
                    billResponse.setPickUpTime(DateTimeFormatUtil.format(
                            bill.getPickUpTime(),
                            DateTimeFormat.dd_SLASH_MM_SLASH_yyyy_SPACE_HH_COLON_mm_COLON_ss
                    ));
                    billResponse.setCreatedDate(DateTimeFormatUtil.format(
                            bill.getCreatedDate(),
                            DateTimeFormat.dd_SLASH_MM_SLASH_yyyy_SPACE_HH_COLON_mm_COLON_ss
                    ));
                    List<BillItemResponse> billItemResponseList = bill.getBillItemList()
                            .stream()
                            .map(billItem -> {
                                ProductResponse productResponse = clientResponse.getProductResponseList()
                                        .stream()
                                        .filter(product -> Objects.equals(product.getId(), billItem.getProductId()))
                                        .findFirst()
                                        .orElse(new ProductResponse());
                                return BillItemResponse.builder()
                                        .quantity(billItem.getQuantity())
                                        .price(billItem.getPrice())
                                        .productResponse(productResponse)
                                        .build();
                            })
                            .toList();
                    billResponse.setBillItemResponseList(billItemResponseList);
                    return billResponse;
                })
                .toList();
    }

    public BillResponse mapBillToBillResponse(Bill bill) {
        BillResponse billResponse = Mapper.map(bill, BillResponse.class);
        billResponse.setPickUpTime(DateTimeFormatUtil.format(
                bill.getPickUpTime(),
                DateTimeFormat.dd_SLASH_MM_SLASH_yyyy_SPACE_HH_COLON_mm_COLON_ss
        ));
        billResponse.setCreatedDate(DateTimeFormatUtil.format(
                bill.getCreatedDate(),
                DateTimeFormat.dd_SLASH_MM_SLASH_yyyy_SPACE_HH_COLON_mm_COLON_ss
        ));
        return billResponse;
    }
}
