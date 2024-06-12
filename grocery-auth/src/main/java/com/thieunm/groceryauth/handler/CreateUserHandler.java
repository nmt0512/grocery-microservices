package com.thieunm.groceryauth.handler;

import com.thieunm.groceryauth.dto.request.CreateUserRequest;
import com.thieunm.groceryauth.dto.response.CreateUserResponse;
import com.thieunm.groceryauth.dto.response.UserResponse;
import com.thieunm.groceryauth.utils.KeycloakUtil;
import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerybase.exception.BaseException;
import com.thieunm.grocerybase.exception.CommonErrorCode;
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
public class CreateUserHandler extends CommandHandler<CreateUserRequest, CreateUserResponse> {

    private final KeycloakUtil keycloakUtil;
    private final KeycloakSpringBootProperties keycloakSpringBootProperties;

    @Override
    public CreateUserResponse handle(CreateUserRequest requestData) {
        try (Keycloak adminKeycloak = keycloakUtil.getAdminKeycloakInstance()) {
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(requestData.getPassword());
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setCreatedDate(System.currentTimeMillis());

            UserRepresentation userRepresentation = Mapper.map(requestData, UserRepresentation.class);
            userRepresentation.setUsername(requestData.getUsername());
            userRepresentation.setEnabled(true);
            userRepresentation.setEmailVerified(true);
            userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
            userRepresentation.setCreatedTimestamp(System.currentTimeMillis());

            try (Response response = adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .create(userRepresentation)) {
                log.info("Keycloak register user response: {}", response.getStatusInfo().getReasonPhrase());
                if (response.getStatus() == HttpStatus.CREATED.value()) {
                    UserRepresentation createdUserRepresentation = adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                            .users()
                            .searchByUsername(requestData.getUsername(), true)
                            .get(0);
                    adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                            .users()
                            .get(createdUserRepresentation.getId())
                            .roles()
                            .realmLevel()
                            .add(Collections.singletonList(adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                                    .roles()
                                    .get(requestData.getRealmRoles().getRole())
                                    .toRepresentation()));
                    UserResponse userResponse = Mapper.map(createdUserRepresentation, UserResponse.class);
                    userResponse.setRealmRoles(requestData.getRealmRoles());
                    return new CreateUserResponse(userResponse);
                } else {
                    throw new BaseException(CommonErrorCode.KEYCLOAK_OPERATION_ERROR);
                }
            }
        }
    }
}
