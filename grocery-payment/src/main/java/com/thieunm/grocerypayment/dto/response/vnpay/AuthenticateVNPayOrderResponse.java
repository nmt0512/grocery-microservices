package com.thieunm.grocerypayment.dto.response.vnpay;

import com.thieunm.grocerybase.dto.response.QueryResponseData;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateVNPayOrderResponse extends QueryResponseData {
    private String orderStatus;
}
