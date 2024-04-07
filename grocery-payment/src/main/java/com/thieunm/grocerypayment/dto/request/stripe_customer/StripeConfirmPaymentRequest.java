package com.thieunm.grocerypayment.dto.request.stripe_customer;

import com.thieunm.grocerybase.dto.request.CommandRequestData;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StripeConfirmPaymentRequest extends CommandRequestData {
    private Integer totalPrice;
}
