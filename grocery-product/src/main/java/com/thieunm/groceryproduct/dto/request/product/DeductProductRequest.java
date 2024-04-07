package com.thieunm.groceryproduct.dto.request.product;

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
