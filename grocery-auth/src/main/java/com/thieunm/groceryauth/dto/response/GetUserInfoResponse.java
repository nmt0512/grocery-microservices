package com.thieunm.groceryauth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thieunm.groceryauth.enums.RealmRoles;
import com.thieunm.grocerybase.dto.response.QueryResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GetUserInfoResponse extends QueryResponseData {
    private String id;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    @JsonProperty("role")
    private RealmRoles realmRoles;
}
