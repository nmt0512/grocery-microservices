package com.thieunm.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route("grocery-auth", r -> r
                        .path("/api/auth/**")
                        .uri("lb://grocery-auth")
                )
                .route("grocery-product", r -> r
                        .path("/api/product/**", "/api/internal/product/**",
                                "/api/category/**")
                        .uri("lb://grocery-product")
                )
                .route("grocery-cart", r -> r
                        .path("/api/cart/**")
                        .uri("lb://grocery-cart")
                )
                .route("grocery-payment", r -> r
                        .path("/api/payment/**",
                                "/api/bill/**", "/api/internal/bill/**",
                                "/api/staff/bill/**",
                                "/api/device/**",
                                "/api/statistic/**")
                        .uri("lb://grocery-payment"))
                .route("grocery-netty-socket", r -> r
                        .path("/socket.io/**")
                        .uri("http://localhost:8081"))
                .build();
    }
}
