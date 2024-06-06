package com.thieunm.groceryauth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thieunm.grocerybase.dto.response.QueryResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoginResponse extends QueryResponseData {
    private long refreshExpiresIn;
    private String refreshToken;
    @JsonProperty("accessToken")
    private String token;
    private long expiresIn;
    private String tokenType;
}
