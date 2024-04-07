package com.thieunm.groceryauth.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@Configuration
public class KeycloakConfig {

    private static final String keycloakRegistrationId = "keycloak-login";

    @Bean
    public KeycloakSpringBootProperties keycloakSpringBootProperties() {
        return new KeycloakSpringBootProperties();
    }

    @Bean
    public ClientRegistration clientRegistration(ClientRegistrationRepository clientRegistrationRepository) {
        return clientRegistrationRepository.findByRegistrationId(keycloakRegistrationId);
    }

}
