package com.thieunm.grocerypayment.client.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeductProductRequest {
    private Integer productId;
    private Integer deductingQuantity;
}
