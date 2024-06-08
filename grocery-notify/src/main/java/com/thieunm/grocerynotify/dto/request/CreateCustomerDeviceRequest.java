package com.thieunm.grocerynotify.dto.request;

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
