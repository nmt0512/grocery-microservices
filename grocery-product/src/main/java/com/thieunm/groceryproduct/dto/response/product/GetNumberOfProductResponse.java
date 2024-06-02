package com.thieunm.groceryproduct.dto.response.product;

import com.thieunm.grocerybase.dto.response.QueryResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GetNumberOfProductResponse extends QueryResponseData {
    private long numberOfProduct;
}
