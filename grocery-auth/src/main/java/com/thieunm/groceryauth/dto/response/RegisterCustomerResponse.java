package com.thieunm.groceryauth.dto.response;

import com.thieunm.grocerybase.dto.response.CommandResponseData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RegisterCustomerResponse extends CommandResponseData {
    private String registeredPhoneNumber;
}
