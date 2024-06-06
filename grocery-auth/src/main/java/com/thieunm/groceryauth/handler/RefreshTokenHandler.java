package com.thieunm.groceryauth.handler;

import com.thieunm.groceryauth.client.IKeycloakClient;
import com.thieunm.groceryauth.client.dto.request.RefreshTokenClientRequest;
import com.thieunm.groceryauth.client.dto.response.RefreshTokenClientResponse;
import com.thieunm.groceryauth.dto.request.RefreshTokenRequest;
import com.thieunm.groceryauth.dto.response.RefreshTokenResponse;
import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenHandler extends QueryHandler<RefreshTokenRequest, RefreshTokenResponse> {

    private final IKeycloakClient keycloakClient;

    @Override
    public RefreshTokenResponse handle(RefreshTokenRequest requestData) {
        RefreshTokenClientRequest clientRequest = new RefreshTokenClientRequest(requestData.getRefreshToken());
        RefreshTokenClientResponse clientResponse = keycloakClient.refreshToken(clientRequest);
        AccessTokenResponse accessTokenResponse = clientResponse.getAccessTokenResponse();
        return Mapper.map(accessTokenResponse, RefreshTokenResponse.class);
    }
}
