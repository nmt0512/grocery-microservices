package com.thieunm.groceryauth.handler;

import com.thieunm.groceryauth.dto.request.GetUserInfoRequest;
import com.thieunm.groceryauth.dto.response.GetUserInfoResponse;
import com.thieunm.groceryauth.enums.RealmRoles;
import com.thieunm.groceryauth.utils.KeycloakUtil;
import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryutils.JsonWebTokenUtil;
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
public class GetUserInfoHandler extends QueryHandler<GetUserInfoRequest, GetUserInfoResponse> {

    private final KeycloakUtil keycloakUtil;
    private final KeycloakSpringBootProperties keycloakSpringBootProperties;

    @Override
    public GetUserInfoResponse handle(GetUserInfoRequest requestData) {
        String phoneNumber = JsonWebTokenUtil.getPhoneNumber(requestData.getAccessToken());
        try (Keycloak adminKeycloak = keycloakUtil.getAdminKeycloakInstance()) {
            UserRepresentation userRepresentation = adminKeycloak
                    .realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .searchByUsername(phoneNumber, false)
                    .get(0);
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

            // TODO setup base exception to throw

            GetUserInfoResponse response = Mapper.map(userRepresentation, GetUserInfoResponse.class);
            response.setPhoneNumber(phoneNumber);
            response.setRealmRoles(userRole);
            return response;
        }
    }
}
