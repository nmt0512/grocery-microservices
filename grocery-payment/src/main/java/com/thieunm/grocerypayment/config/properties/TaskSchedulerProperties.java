package com.thieunm.grocerypayment.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.scheduler")
@Getter
@Setter
public class TaskSchedulerProperties {
    private int poolSize;
    private String threadNamePrefix;
}
