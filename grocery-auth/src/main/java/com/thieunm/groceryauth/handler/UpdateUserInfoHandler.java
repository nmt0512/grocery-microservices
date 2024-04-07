package com.thieunm.groceryauth.handler;

import com.thieunm.groceryauth.dto.request.UpdateUserInfoRequest;
import com.thieunm.groceryauth.dto.response.UpdateUserInfoResponse;
import com.thieunm.groceryauth.utils.KeycloakUtil;
import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoHandler extends CommandHandler<UpdateUserInfoRequest, UpdateUserInfoResponse> {

    private final KeycloakUtil keycloakUtil;
    private final KeycloakSpringBootProperties keycloakSpringBootProperties;

    @Override
    public UpdateUserInfoResponse handle(UpdateUserInfoRequest requestData) {
        String phoneNumber = JsonWebTokenUtil.getPhoneNumber(requestData.getAccessToken());
        try (Keycloak adminKeycloak = keycloakUtil.getAdminKeycloakInstance()) {

            UserRepresentation userRepresentation = adminKeycloak
                    .realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .searchByUsername(phoneNumber, false)
                    .get(0);
            userRepresentation.setFirstName(requestData.getFirstName());
            userRepresentation.setLastName(requestData.getLastName());
            userRepresentation.setEmail(requestData.getEmail());

            adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .get(userRepresentation.getId())
                    .update(userRepresentation);
            UserRepresentation updatedUserRepresentation = adminKeycloak
                    .realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .get(userRepresentation.getId())
                    .toRepresentation();
            return Mapper.map(updatedUserRepresentation, UpdateUserInfoResponse.class);
        }
    }
}
