package com.thieunm.groceryauth.dto.request;

import com.thieunm.grocerybase.dto.request.QueryRequestData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoginRequest extends QueryRequestData {
    private String phoneNumber;
    private String password;
}
