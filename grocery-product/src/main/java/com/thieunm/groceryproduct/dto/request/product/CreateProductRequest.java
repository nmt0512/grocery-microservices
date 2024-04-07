package com.thieunm.groceryproduct.dto.request.product;

import com.thieunm.grocerybase.dto.request.CommandRequestData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest extends CommandRequestData {
    private String name;
    private Integer categoryId;
    private Integer quantity;
    private Integer unitPrice;
    private List<String> imageUrlList;
}
