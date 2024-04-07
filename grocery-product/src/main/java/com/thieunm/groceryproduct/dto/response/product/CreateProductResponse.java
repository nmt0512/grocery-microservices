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
public class CreateProductResponse extends CommandResponseData {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String code;
    private Integer quantity;
    private Integer unitPrice;
}
