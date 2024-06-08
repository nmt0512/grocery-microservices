package com.thieunm.grocerynotify.kafka.consumer;

import com.thieunm.grocerybase.enums.NotifyType;
import com.thieunm.grocerynotify.kafka.message.NotifyRequest;
import com.thieunm.grocerynotify.service.PushService;
import com.thieunm.groceryutils.JsonMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PushService pushService;

    @KafkaListener(topics = "${spring.kafka.topics.notify}", groupId = "${spring.kafka.consumer.group-id}")
    @Async
    public void listenAndSendNotification(String message) {
        NotifyRequest notifyRequest = JsonMapperUtil.parseJsonToObject(message, NotifyRequest.class);
        if (notifyRequest.getType() == NotifyType.PUSH) {
            pushService.pushNotification(notifyRequest);
        }
    }
}
