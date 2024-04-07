package com.thieunm.groceryproduct.dto.response.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thieunm.grocerybase.dto.response.QueryResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCategoryResponse extends QueryResponseData {
    @JsonProperty("categoryList")
    private List<CategoryResponse> categoryResponseList;
}
