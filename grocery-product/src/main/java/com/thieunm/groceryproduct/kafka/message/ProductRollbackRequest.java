package com.thieunm.groceryproduct.kafka.message;

import com.thieunm.groceryproduct.dto.request.product.DeductingProduct;
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
