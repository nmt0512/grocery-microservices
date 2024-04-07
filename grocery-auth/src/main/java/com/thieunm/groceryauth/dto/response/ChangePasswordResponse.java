package com.thieunm.groceryauth.dto.response;

import com.thieunm.grocerybase.dto.response.CommandResponseData;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ChangePasswordResponse extends CommandResponseData {
    private boolean isPasswordChanged;
}
