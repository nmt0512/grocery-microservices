package com.thieunm.groceryauth.handler;

import com.thieunm.groceryauth.dto.request.ChangePasswordRequest;
import com.thieunm.groceryauth.dto.request.CustomerLoginRequest;
import com.thieunm.groceryauth.dto.response.ChangePasswordResponse;
import com.thieunm.groceryauth.utils.KeycloakUtil;
import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChangePasswordHandler extends CommandHandler<ChangePasswordRequest, ChangePasswordResponse> {

    private final KeycloakUtil keycloakUtil;
    private final KeycloakSpringBootProperties keycloakSpringBootProperties;
    private final CustomerLoginHandler customerLoginHandler;

    @Override
    public ChangePasswordResponse handle(ChangePasswordRequest requestData) {
        String phoneNumber = JsonWebTokenUtil.getPhoneNumber(requestData.getAccessToken());
        customerLoginHandler.handle(new CustomerLoginRequest(phoneNumber, requestData.getOldPassword()));
        try (Keycloak adminKeycloak = keycloakUtil.getAdminKeycloakInstance()) {
            UserRepresentation userRepresentation = adminKeycloak
                    .realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .searchByUsername(phoneNumber, false)
                    .get(0);
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(requestData.getNewPassword());
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setCreatedDate(System.currentTimeMillis());

            userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

            adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .get(userRepresentation.getId())
                    .update(userRepresentation);
            return new ChangePasswordResponse(true);
        }
    }
}
