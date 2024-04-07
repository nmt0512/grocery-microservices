package com.thieunm.groceryauth.handler;

import com.thieunm.groceryauth.dto.request.RegisterCustomerRequest;
import com.thieunm.groceryauth.dto.response.RegisterCustomerResponse;
import com.thieunm.groceryauth.enums.RealmRoles;
import com.thieunm.groceryauth.utils.KeycloakUtil;
import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryutils.Mapper;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterCustomerHandler extends CommandHandler<RegisterCustomerRequest, RegisterCustomerResponse> {

    private final KeycloakUtil keycloakUtil;
    private final KeycloakSpringBootProperties keycloakSpringBootProperties;

    @Override
    public RegisterCustomerResponse handle(RegisterCustomerRequest requestData) {
        try (Keycloak adminKeycloak = keycloakUtil.getAdminKeycloakInstance()) {
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(requestData.getPassword());
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setCreatedDate(System.currentTimeMillis());

            UserRepresentation userRepresentation = Mapper.map(requestData, UserRepresentation.class);
            userRepresentation.setUsername(requestData.getPhoneNumber());
            userRepresentation.setEnabled(true);
            userRepresentation.setEmailVerified(true);
            userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
            userRepresentation.setCreatedTimestamp(System.currentTimeMillis());

            try (Response response = adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .create(userRepresentation)) {
                log.info("Keycloak register user response status: {}", response.getStatus());
                String createdUserId = adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                        .users()
                        .searchByUsername(requestData.getPhoneNumber(), true)
                        .get(0)
                        .getId();
                adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                        .users()
                        .get(createdUserId)
                        .roles()
                        .realmLevel()
                        .add(Collections.singletonList(adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                                .roles()
                                .get(RealmRoles.CUSTOMER.getRole())
                                .toRepresentation()));
                if (response.getStatus() == HttpStatus.CREATED.value()) {
                    return RegisterCustomerResponse.builder()
                            .registeredPhoneNumber(requestData.getPhoneNumber())
                            .build();
                }
            }
        }
        return null;
    }
}
