package com.thieunm.groceryproduct.dto.response.category;

import com.thieunm.grocerybase.dto.response.QueryResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GetNumberOfCategoryResponse extends QueryResponseData {
    private long numberOfCategory;
}
