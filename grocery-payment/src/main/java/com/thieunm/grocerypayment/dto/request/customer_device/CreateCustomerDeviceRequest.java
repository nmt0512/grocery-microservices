package com.thieunm.grocerypayment.dto.request.customer_device;

import com.thieunm.grocerybase.dto.request.CommandRequestData;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDeviceRequest extends CommandRequestData {
    private String deviceId;
    private String deviceToken;
}
