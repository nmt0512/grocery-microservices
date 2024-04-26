package com.thieunm.grocerypayment.handler.stripe_customer;

import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.EphemeralKey;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.EphemeralKeyCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerypayment.config.properties.StripeProperties;
import com.thieunm.grocerypayment.dto.request.stripe_customer.StripeConfirmPaymentRequest;
import com.thieunm.grocerypayment.dto.response.stripe_customer.StripeConfirmPaymentResponse;
import com.thieunm.grocerypayment.entity.StripeCustomer;
import com.thieunm.grocerypayment.repository.StripeCustomerRepository;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StripeConfirmPaymentHandler extends CommandHandler<StripeConfirmPaymentRequest, StripeConfirmPaymentResponse> {

    private static final String PAYMENT_CURRENCY = "VND";

    private final StripeProperties stripeProperties;
    private final StripeCustomerRepository stripeCustomerRepository;

    @Override
    @Transactional
    @SneakyThrows
    public StripeConfirmPaymentResponse handle(StripeConfirmPaymentRequest requestData) {

        Stripe.apiKey = stripeProperties.getApiKey();

        String userId = JsonWebTokenUtil.getUserId(requestData.getAccessToken());
        Optional<StripeCustomer> stripeCustomerOptional = stripeCustomerRepository.findByUserId(userId);
        String stripeCustomerId;

        if (stripeCustomerOptional.isEmpty()) {
            String userPhoneNumber = JsonWebTokenUtil.getPhoneNumber(requestData.getAccessToken());
            CustomerCreateParams customerParams = CustomerCreateParams
                    .builder()
                    .setName(userPhoneNumber)
                    .setPhone(userPhoneNumber)
                    .build();
            Customer customer = Customer.create(customerParams);
            StripeCustomer stripeCustomer = StripeCustomer
                    .builder()
                    .stripeCustomerId(customer.getId())
                    .userId(userId)
                    .build();
            stripeCustomerRepository.save(stripeCustomer);
            stripeCustomerId = customer.getId();
        } else {
            stripeCustomerId = stripeCustomerOptional.get().getStripeCustomerId();
        }

        EphemeralKeyCreateParams ephemeralKeyParams = EphemeralKeyCreateParams.builder()
                .setStripeVersion(stripeProperties.getVersion())
                .setCustomer(stripeCustomerId)
                .build();

        EphemeralKey ephemeralKey = EphemeralKey.create(ephemeralKeyParams);

        PaymentIntentCreateParams paymentIntentParams = PaymentIntentCreateParams.builder()
                .setAmount(requestData.getTotalPrice().longValue())
                .setCurrency(PAYMENT_CURRENCY)
                .setCustomer(stripeCustomerId)
                .setAutomaticPaymentMethods(PaymentIntentCreateParams
                        .AutomaticPaymentMethods
                        .builder()
                        .setEnabled(true)
                        .build()
                )
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentParams);
        return StripeConfirmPaymentResponse.builder()
                .paymentIntent(paymentIntent.getClientSecret())
                .ephemeralKey(ephemeralKey.getSecret())
                .customer(stripeCustomerId)
                .publishableKey(stripeProperties.getPublishableKey())
                .build();
    }
}
