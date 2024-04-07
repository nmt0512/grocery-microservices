package com.thieunm.grocerycart.dto.response;

import com.thieunm.grocerybase.dto.response.CommandResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartResponse extends CommandResponseData {
    private String id;
    private Integer productId;
    private Integer quantity;
    private Integer totalPrice;
}
