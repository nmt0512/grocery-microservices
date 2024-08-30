package com.thieunm.grocerypayment.dto.response.vnpay;

import com.thieunm.grocerybase.dto.response.CommandResponseData;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateVNPayOrderResponse extends CommandResponseData {
    private String vnPayUrl;
}
