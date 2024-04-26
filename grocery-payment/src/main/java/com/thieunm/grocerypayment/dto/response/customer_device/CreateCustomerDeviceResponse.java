package com.thieunm.grocerypayment.dto.response.customer_device;

import com.thieunm.grocerybase.dto.response.CommandResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateCustomerDeviceResponse extends CommandResponseData {
    private Integer id;
    private String deviceId;
    private String deviceToken;
}
