package com.thieunm.grocerysecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(
                                "/api/category/**",
                                "/api/product", "/api/product/**",
                                "/api/cart/**",
                                "/api/payment/**"
                        ).hasRole("CUSTOMER")
                        .requestMatchers(
                                "/api/bill", "/api/bill/**"
                        ).hasAnyRole("CUSTOMER", "STAFF")
                        .requestMatchers("/api/staff").hasRole("STAFF")
                        .anyRequest()
                        .permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter())
                ))
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new CustomRolesConverter());
        return converter;
    }

}
