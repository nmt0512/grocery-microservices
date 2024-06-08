package com.thieunm.grocerypayment.kafka.producer;

import com.thieunm.grocerypayment.kafka.message.NotifyRequest;
import com.thieunm.groceryutils.JsonMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class NotifyProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topics.notify}")
    private String notifyTopic;

    public NotifyProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void sendNotifyRequest(NotifyRequest request) {
        if (request.getTo() != null && !request.getTo().isEmpty()) {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(notifyTopic, JsonMapperUtil.toString(request));
            future.thenApply(result -> {
                log.info("Message [{}] delivered with offset {}", JsonMapperUtil.toString(request), result.getRecordMetadata().offset());
                return null;
            }).exceptionally(exception -> {
                log.warn("Unable to deliver message [{}]. {}", JsonMapperUtil.toString(request), exception.getMessage());
                return null;
            });
        }
    }
}
