package com.thieunm.grocerypayment.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.thieunm.grocerypayment.config.properties.SocketIOProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SocketIOConfig {

    private final SocketIOProperties properties;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(properties.getHostname());
        config.setPort(properties.getPort());
        config.setPingTimeout(properties.getTimeout());
        config.setUpgradeTimeout(properties.getTimeout());
        config.setFirstDataTimeout(properties.getTimeout());
        return new SocketIOServer(config);
    }
}
