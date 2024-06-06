package com.thieunm.groceryauth.handler;

import com.thieunm.groceryauth.dto.request.DeleteUserByIdRequest;
import com.thieunm.groceryauth.dto.response.DeleteUserByIdResponse;
import com.thieunm.groceryauth.utils.KeycloakUtil;
import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteUserByIdHandler extends CommandHandler<DeleteUserByIdRequest, DeleteUserByIdResponse> {

    private final KeycloakUtil keycloakUtil;
    private final KeycloakSpringBootProperties keycloakSpringBootProperties;

    @Override
    public DeleteUserByIdResponse handle(DeleteUserByIdRequest requestData) {
        try (Keycloak adminKeycloak = keycloakUtil.getAdminKeycloakInstance()) {
            try (Response response = adminKeycloak.realm(keycloakSpringBootProperties.getRealm())
                    .users()
                    .delete(requestData.getId())) {
                log.info(String.valueOf(response.getStatus()));
                if (response.getStatus() == HttpStatus.NO_CONTENT.value()) {
                    return new DeleteUserByIdResponse(requestData.getId());
                }
            }
        }
        return null;
    }
}
