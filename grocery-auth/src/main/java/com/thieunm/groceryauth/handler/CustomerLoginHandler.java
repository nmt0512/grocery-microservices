package com.thieunm.groceryauth.handler;

import com.thieunm.groceryauth.dto.request.CustomerLoginRequest;
import com.thieunm.groceryauth.dto.response.CustomerLoginResponse;
import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerLoginHandler extends QueryHandler<CustomerLoginRequest, CustomerLoginResponse> {

    private final KeycloakSpringBootProperties keycloakSpringBootProperties;

    private final ClientRegistration clientRegistration;

    @Override
    public CustomerLoginResponse handle(CustomerLoginRequest requestData) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakSpringBootProperties.getAuthServerUrl())
                .realm(keycloakSpringBootProperties.getRealm())
                .clientId(clientRegistration.getClientId())
                .clientSecret(clientRegistration.getClientSecret())
                .grantType(clientRegistration.getAuthorizationGrantType().getValue())
                .username(requestData.getPhoneNumber())
                .password(requestData.getPassword())
                .build();
        AccessTokenResponse accessTokenResponse = keycloak.tokenManager().getAccessToken();
        keycloak.close();
        return Mapper.map(accessTokenResponse, CustomerLoginResponse.class);
    }
}
