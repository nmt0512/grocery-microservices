package com.thieunm.apigateway.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityWebFilterChain permitAllSecurityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .securityMatcher(ServerWebExchangeMatchers.pathMatchers(
                        "/socket.io/**",
                        "/api/auth/adminLogin",
                        "/api/auth/login",
                        "/api/auth/staffLogin",
                        "/api/auth/registerCustomer",
                        "/api/auth/refreshToken"
                ))
                .authorizeExchange(authorize -> authorize.anyExchange().permitAll())
                .addFilterBefore(
                        new PermitAllWebFilter(
                                "/socket.io/**",
                                "/api/auth/adminLogin",
                                "/api/auth/login",
                                "/api/auth/staffLogin",
                                "/api/auth/registerCustomer",
                                "/api/auth/refreshToken"
                        ),
                        SecurityWebFiltersOrder.AUTHENTICATION
                );
        return serverHttpSecurity.build();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(
                        authorizeExchangeSpec -> authorizeExchangeSpec
                                .pathMatchers("/api/cart/**",
                                        "/api/payment/**",
                                        "/api/auth/password",
                                        "/api/auth/userInfo",
                                        "/api/device").hasRole("CUSTOMER")
                                .pathMatchers("/api/staff/bill/status").hasRole("STAFF")
                                .pathMatchers("/api/statistic/**",
                                        "/api/auth/admin/**").hasRole("ADMIN")
                                .pathMatchers("/api/product/**",
                                        "/api/category/**").hasAnyRole("CUSTOMER", "ADMIN")
                                .pathMatchers("/api/bill/**").hasAnyRole("CUSTOMER", "STAFF")
                                .anyExchange().denyAll()
                )
                .oauth2ResourceServer(oAuth2 -> oAuth2
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtMonoConverter())));
        return serverHttpSecurity.build();
    }

    @Bean
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtMonoConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new CustomRolesConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
