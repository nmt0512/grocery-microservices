package com.thieunm.grocerypayment.dto.request.vnpay;

import com.thieunm.grocerybase.dto.request.CommandRequestData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateVNPayOrderRequest extends CommandRequestData {
    private String urlReturn;
    private String orderInfo;
    private int orderTotal;
}
