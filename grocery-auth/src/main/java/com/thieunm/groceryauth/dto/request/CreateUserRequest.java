package com.thieunm.groceryauth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thieunm.groceryauth.enums.RealmRoles;
import com.thieunm.grocerybase.dto.request.CommandRequestData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest extends CommandRequestData {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    @JsonProperty("role")
    private RealmRoles realmRoles;
}
