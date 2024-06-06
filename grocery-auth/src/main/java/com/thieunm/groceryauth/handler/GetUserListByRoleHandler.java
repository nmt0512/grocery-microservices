package com.thieunm.groceryauth.handler;

import com.thieunm.groceryauth.dto.request.GetUserListByRoleRequest;
import com.thieunm.groceryauth.dto.response.GetUserListByRoleResponse;
import com.thieunm.groceryauth.dto.response.UserResponse;
import com.thieunm.groceryauth.enums.RealmRoles;
import com.thieunm.groceryauth.utils.KeycloakUtil;
import com.thieunm.grocerybase.cqrs.query.QueryHandler;
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
public class GetUserListByRoleHandler extends QueryHandler<GetUserListByRoleRequest, GetUserListByRoleResponse> {

    private final KeycloakUtil keycloakUtil;
    private final KeycloakSpringBootProperties keycloakSpringBootProperties;

    @Override
    public GetUserListByRoleResponse handle(GetUserListByRoleRequest requestData) {
        try (Keycloak adminKeycloak = keycloakUtil.getAdminKeycloakInstance()) {
            List<UserRepresentation> userRepresentationList = adminKeycloak
                    .realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .list();
            List<UserResponse> userResponseList = userRepresentationList.stream()
                    .map(userRepresentation -> {
                        List<RoleRepresentation> userRoleRepresentationList = adminKeycloak
                                .realm(keycloakSpringBootProperties.getRealm())
                                .users()
                                .get(userRepresentation.getId())
                                .roles()
                                .realmLevel()
                                .listAll();
                        RealmRoles userRole = userRoleRepresentationList
                                .stream()
                                .filter(roleRepresentation -> Arrays
                                        .stream(RealmRoles.values())
                                        .anyMatch(realmRoles -> realmRoles
                                                .getRole()
                                                .equals(roleRepresentation.getName())
                                        ))
                                .map(roleRepresentation -> RealmRoles
                                        .valueOf(roleRepresentation
                                                .getName()
                                                .toUpperCase())
                                )
                                .findAny()
                                .get();
                        UserResponse userResponse = Mapper.map(userRepresentation, UserResponse.class);
                        userResponse.setRealmRoles(userRole);
                        return userResponse;
                    })
                    .filter(userResponse -> userResponse.getRealmRoles().equals(requestData.getRole()))
                    .toList();
            return new GetUserListByRoleResponse(userResponseList);
        }
    }
}
