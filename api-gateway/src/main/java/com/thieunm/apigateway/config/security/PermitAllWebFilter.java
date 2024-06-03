package com.thieunm.apigateway.config.security;

import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class PermitAllWebFilter implements WebFilter {

    private final ServerWebExchangeMatcher permitAllMatcher;

    public PermitAllWebFilter(String... permitAllPatterns) {
        this.permitAllMatcher = ServerWebExchangeMatchers.pathMatchers(permitAllPatterns);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return permitAllMatcher.matches(exchange)
                .flatMap(matchResult -> {
                    if (matchResult.isMatch()) {
                        return chain.filter(exchange);
                    } else {
                        return Mono.error(new Exception("Access denied"));
                    }
                });
    }
}
