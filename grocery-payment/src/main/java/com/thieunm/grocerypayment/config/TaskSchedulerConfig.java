package com.thieunm.grocerypayment.config;

import com.thieunm.grocerypayment.config.properties.TaskSchedulerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class TaskSchedulerConfig {

    private final TaskSchedulerProperties properties;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(properties.getPoolSize());
        threadPoolTaskScheduler.setThreadNamePrefix(properties.getThreadNamePrefix());
        return threadPoolTaskScheduler;
    }
}
