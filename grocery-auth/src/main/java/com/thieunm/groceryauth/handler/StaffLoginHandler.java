package com.thieunm.groceryauth.handler;

import com.thieunm.grocerybase.constant.AccountRoles;
import com.thieunm.groceryauth.dto.request.StaffLoginRequest;
import com.thieunm.groceryauth.dto.response.StaffLoginResponse;
import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryutils.JsonMapperUtil;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffLoginHandler extends QueryHandler<StaffLoginRequest, StaffLoginResponse> {

    private final KeycloakSpringBootProperties keycloakSpringBootProperties;

    private final ClientRegistration clientRegistration;

    @Override
    public StaffLoginResponse handle(StaffLoginRequest requestData) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakSpringBootProperties.getAuthServerUrl())
                .realm(keycloakSpringBootProperties.getRealm())
                .clientId(clientRegistration.getClientId())
                .clientSecret(clientRegistration.getClientSecret())
                .grantType(clientRegistration.getAuthorizationGrantType().getValue())
                .username(requestData.getUsername())
                .password(requestData.getPassword())
                .build();
        AccessTokenResponse accessTokenResponse = keycloak.tokenManager().grantToken();
        keycloak.close();
        List<String> roleList = JsonWebTokenUtil.getRoleList(accessTokenResponse.getToken());
        log.info("roles: {}", JsonMapperUtil.toString(roleList));
        boolean isStaffRole = roleList.stream().anyMatch(role -> role.equals(AccountRoles.STAFF));
        if (isStaffRole) {
            return Mapper.map(accessTokenResponse, StaffLoginResponse.class);
        } else {
            return null;
        }
    }
}
