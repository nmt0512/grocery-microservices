package com.thieunm.groceryproduct.dto.request.product;

import com.thieunm.grocerybase.dto.request.QueryRequestData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GetProductByCategoryIdRequest extends QueryRequestData {
    private int categoryId;
}
