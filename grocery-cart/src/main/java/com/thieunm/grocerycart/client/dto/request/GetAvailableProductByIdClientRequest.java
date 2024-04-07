package com.thieunm.grocerycart.client.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAvailableProductByIdClientRequest {
    private Integer productId;
    private Integer quantity;
}
