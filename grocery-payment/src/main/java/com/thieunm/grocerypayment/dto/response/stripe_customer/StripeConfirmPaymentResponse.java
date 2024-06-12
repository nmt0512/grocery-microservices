package com.thieunm.grocerypayment.dto.response.stripe_customer;

import com.thieunm.grocerybase.dto.response.CommandResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StripeConfirmPaymentResponse extends CommandResponseData {
    private String paymentIntentId;
    private String paymentIntentClientSecret;
    private String ephemeralKey;
    private String customer;
    private String publishableKey;
}
