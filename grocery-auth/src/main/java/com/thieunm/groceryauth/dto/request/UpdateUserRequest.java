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
public class UpdateUserRequest extends CommandRequestData {
    private String id;
    @JsonProperty("role")
    private RealmRoles realmRoles;
    private String firstName;
    private String lastName;
    private String email;
}
