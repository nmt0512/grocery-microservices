package com.thieunm.groceryauth.client;

import com.thieunm.groceryauth.client.dto.request.RefreshTokenClientRequest;
import com.thieunm.groceryauth.client.dto.response.RefreshTokenClientResponse;

public interface IKeycloakClient {
    RefreshTokenClientResponse refreshToken(RefreshTokenClientRequest request);
}
