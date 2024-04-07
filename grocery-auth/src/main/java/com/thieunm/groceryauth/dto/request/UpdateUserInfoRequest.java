package com.thieunm.groceryauth.dto.request;

import com.thieunm.grocerybase.dto.request.CommandRequestData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInfoRequest extends CommandRequestData {
    private String firstName;
    private String lastName;
    private String email;
}
