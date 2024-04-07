package com.thieunm.grocerycart.client.dto.response;

import com.thieunm.grocerycart.dto.response.ProductResponse;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAvailableProductByIdClientResponse {
    private boolean isAvailable;
    private ProductResponse product;
}
