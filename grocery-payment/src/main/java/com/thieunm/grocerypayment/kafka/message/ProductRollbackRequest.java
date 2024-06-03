package com.thieunm.grocerypayment.kafka.message;

import com.thieunm.grocerypayment.client.product.dto.request.DeductingProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRollbackRequest {
    private List<DeductingProduct> deductingProductList;
}
