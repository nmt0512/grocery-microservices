package com.thieunm.grocerypayment.client.product.dto.response;

import com.thieunm.grocerybase.dto.response.CommandResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DeductQuantityListProductClientResponse extends CommandResponseData {
    private List<Integer> deductedProductIdList;
}
