package com.thieunm.grocerypayment.dto.request.vnpay;

import com.thieunm.grocerybase.dto.request.QueryRequestData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateVNPayOrderRequest extends QueryRequestData {
    private HttpServletRequest httpServletRequest;
}
