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
public class DeductQuantityListProductRequest extends CommandRequestData {
    private List<DeductProductRequest> deductProductRequestList;
}
