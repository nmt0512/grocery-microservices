package com.thieunm.groceryproduct.dto.response.product;

import com.thieunm.grocerybase.dto.response.CommandResponseData;
import com.thieunm.groceryproduct.dto.request.product.DeductingProduct;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DeductQuantityListProductResponse extends CommandResponseData {
    private List<DeductingProduct> deductingProductList;
}
