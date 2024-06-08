package com.thieunm.grocerynotify.service;

import com.google.firebase.messaging.*;
import com.thieunm.grocerynotify.entity.CustomerDevice;
import com.thieunm.grocerynotify.kafka.message.NotifyRequest;
import com.thieunm.grocerynotify.repository.CustomerDeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PushService {

    private final FirebaseMessaging firebaseMessaging;
    private final CustomerDeviceRepository customerDeviceRepository;

    public void pushNotification(NotifyRequest request) {
        List<CustomerDevice> customerDeviceList = customerDeviceRepository.findByCustomerId(request.getTo());
        List<String> deviceTokenList = customerDeviceList
                .stream()
                .map(CustomerDevice::getDeviceToken)
                .toList();
        MulticastMessage multicastMessage = MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(request.getTitle())
                        .setBody(request.getBody())
                        .build())
                .addAllTokens(deviceTokenList)
                .build();
        try {
            BatchResponse batchResponse = firebaseMessaging.sendEachForMulticast(multicastMessage);
            log.info("Number of successfully sent notification via Firebase: {}", batchResponse.getSuccessCount());
        } catch (FirebaseMessagingException exception) {
            exception.printStackTrace();
        }
    }
}
