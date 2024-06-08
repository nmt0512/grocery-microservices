package com.thieunm.grocerynotify.handler;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerynotify.dto.request.CreateCustomerDeviceRequest;
import com.thieunm.grocerynotify.dto.response.CreateCustomerDeviceResponse;
import com.thieunm.grocerynotify.entity.CustomerDevice;
import com.thieunm.grocerynotify.repository.CustomerDeviceRepository;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateCustomerDeviceHandler extends CommandHandler<CreateCustomerDeviceRequest, CreateCustomerDeviceResponse> {

    private final CustomerDeviceRepository customerDeviceRepository;

    @Override
    public CreateCustomerDeviceResponse handle(CreateCustomerDeviceRequest requestData) {
        String customerId = JsonWebTokenUtil.getUserId(requestData.getAccessToken());
        CustomerDevice customerDevice = CustomerDevice.builder()
                .customerId(customerId)
                .deviceId(requestData.getDeviceId())
                .deviceToken(requestData.getDeviceToken())
                .build();
        try {
            customerDevice = customerDeviceRepository.save(customerDevice);
            return Mapper.map(customerDevice, CreateCustomerDeviceResponse.class);
        } catch (DataIntegrityViolationException exception) {
            return null;
        }
    }
}
