package com.thieunm.grocerycart.kafka.consumer;

import com.thieunm.grocerycart.entity.Cart;
import com.thieunm.grocerycart.kafka.message.CartRollbackRequest;
import com.thieunm.grocerycart.repository.CartRepository;
import com.thieunm.groceryutils.JsonMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    private final CartRepository cartRepository;

    @KafkaListener(topics = "${spring.kafka.topics.cart}", groupId = "${spring.kafka.consumer.group-id}")
    @Async
    public void listenAndRollback(String message) {
        CartRollbackRequest rollbackRequest = JsonMapperUtil.parseJsonToObject(message, CartRollbackRequest.class);
        List<Cart> cartList = rollbackRequest.getInternalCartResponseList()
                .stream()
                .map(internalCartResponse -> Cart.builder()
                        .id(UUID.randomUUID().toString())
                        .productId(internalCartResponse.getProductId())
                        .userId(internalCartResponse.getUserId())
                        .quantity(internalCartResponse.getQuantity())
                        .totalPrice(internalCartResponse.getTotalPrice())
                        .build())
                .toList();
        cartRepository.saveAll(cartList);
    }
}
