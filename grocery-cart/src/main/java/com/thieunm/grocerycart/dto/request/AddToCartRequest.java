package com.thieunm.grocerycart.dto.request;

import com.thieunm.grocerybase.dto.request.CommandRequestData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest extends CommandRequestData {
    private Integer productId;
    private Integer quantity;
}
