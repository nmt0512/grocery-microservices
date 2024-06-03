package com.thieunm.apigateway.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(
                        authorizeExchangeSpec -> authorizeExchangeSpec
                                .pathMatchers("/api/cart/**",
                                        "/api/payment/**",
                                        "/api/auth/password",
                                        "/api/auth/userInfo").hasRole("CUSTOMER")
                                .pathMatchers("/api/staff/bill/status").hasRole("STAFF")
                                .pathMatchers("/api/statistic/**").hasRole("ADMIN")
                                .pathMatchers("/api/product/**",
                                        "/api/category/**").hasAnyRole("CUSTOMER", "ADMIN")
                                .pathMatchers("/api/bill/**").hasAnyRole("CUSTOMER", "STAFF")
                                .pathMatchers("/api/auth/**").permitAll()
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
