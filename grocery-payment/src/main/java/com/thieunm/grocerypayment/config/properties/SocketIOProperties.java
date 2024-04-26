package com.thieunm.grocerypayment.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "socket-io")
@Getter
@Setter
public class SocketIOProperties {
    private String hostname;
    private int port;
    private int timeout;
}
