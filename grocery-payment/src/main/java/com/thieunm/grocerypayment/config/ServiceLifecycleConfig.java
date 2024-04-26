package com.thieunm.grocerypayment.config;

import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ServiceLifecycleConfig {

    private final SocketIOServer socketIOServer;

    @PostConstruct
    public void startSocketIOServer() {
        socketIOServer.start();
    }

    @PreDestroy
    public void stopSocketIOServer() {
        socketIOServer.stop();
    }
}
