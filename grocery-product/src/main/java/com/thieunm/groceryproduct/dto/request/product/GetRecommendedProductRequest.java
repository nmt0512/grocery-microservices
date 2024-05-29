package com.thieunm.groceryproduct.dto.request.product;

import com.thieunm.grocerybase.dto.request.QueryRequestData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetRecommendedProductRequest extends QueryRequestData {
}
