package com.thieunm.groceryproduct.kafka.consumer;

import com.thieunm.groceryproduct.entity.Product;
import com.thieunm.groceryproduct.kafka.message.ProductRollbackRequest;
import com.thieunm.groceryproduct.repository.ProductRepository;
import com.thieunm.groceryutils.JsonMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    private final ProductRepository productRepository;

    @KafkaListener(topics = "${spring.kafka.topics.product}", groupId = "${spring.kafka.consumer.group-id}")
    @Async
    public void listenAndRollback(String message) {
        ProductRollbackRequest rollbackRequest = JsonMapperUtil.parseJsonToObject(message, ProductRollbackRequest.class);
        rollbackRequest.getDeductingProductList().forEach(deductingProduct -> {
            Product product = productRepository.findById(deductingProduct.getProductId()).get();
            int rollbackQuantity = product.getQuantity() + deductingProduct.getDeductingQuantity();
            product.setQuantity(rollbackQuantity);
            productRepository.save(product);
        });
    }
}
