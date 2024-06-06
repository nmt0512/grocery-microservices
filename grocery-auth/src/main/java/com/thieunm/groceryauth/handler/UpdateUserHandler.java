package com.thieunm.groceryauth.handler;

import com.thieunm.groceryauth.dto.request.UpdateUserRequest;
import com.thieunm.groceryauth.dto.response.UpdateUserResponse;
import com.thieunm.groceryauth.dto.response.UserResponse;
import com.thieunm.groceryauth.enums.RealmRoles;
import com.thieunm.groceryauth.utils.KeycloakUtil;
import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateUserHandler extends CommandHandler<UpdateUserRequest, UpdateUserResponse> {

    private final KeycloakUtil keycloakUtil;
    private final KeycloakSpringBootProperties keycloakSpringBootProperties;

    @Override
    public UpdateUserResponse handle(UpdateUserRequest requestData) {
        try (Keycloak adminKeycloak = keycloakUtil.getAdminKeycloakInstance()) {

            // GET Old User Representation to Update
            UserRepresentation userRepresentation = adminKeycloak
                    .realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .get(requestData.getId())
                    .toRepresentation();

            // UPDATE User except User Role
            userRepresentation.setFirstName(requestData.getFirstName());
            userRepresentation.setLastName(requestData.getLastName());
            userRepresentation.setEmail(requestData.getEmail());
            adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .get(userRepresentation.getId())
                    .update(userRepresentation);

            // GET all old Roles of Updated User
            List<RoleRepresentation> oldRoleRepresentationList = adminKeycloak
                    .realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .get(userRepresentation.getId())
                    .roles()
                    .realmLevel()
                    .listAll();

            // REMOVE all old Roles of Updated User
            adminKeycloak
                    .realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .get(userRepresentation.getId())
                    .roles()
                    .realmLevel()
                    .remove(oldRoleRepresentationList);

            // REMOVE old Role and ADD new Role to List
            oldRoleRepresentationList.removeIf(roleRepresentation -> Arrays
                    .stream(RealmRoles.values())
                    .anyMatch(realmRoles -> realmRoles
                            .getRole()
                            .equals(roleRepresentation.getName())
                    ));
            oldRoleRepresentationList.add(adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                    .roles()
                    .get(requestData.getRealmRoles().getRole())
                    .toRepresentation());

            // SAVE new Role List to Updated User
            adminKeycloak
                    .realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .get(userRepresentation.getId())
                    .roles()
                    .realmLevel()
                    .add(oldRoleRepresentationList);

            // RESPONSE to Client
            UserResponse userResponse = Mapper.map(userRepresentation, UserResponse.class);
            userResponse.setRealmRoles(requestData.getRealmRoles());
            return new UpdateUserResponse(userResponse);
        }
    }
}
