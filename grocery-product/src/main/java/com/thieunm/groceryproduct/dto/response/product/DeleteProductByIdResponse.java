package com.thieunm.groceryproduct.dto.response.product;

import com.thieunm.grocerybase.dto.response.CommandResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductByIdResponse extends CommandResponseData {
    private int deletedProductId;
}
