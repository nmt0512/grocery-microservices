package com.thieunm.groceryproduct.dto.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thieunm.grocerybase.dto.response.QueryResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductResponse extends QueryResponseData {
    @JsonProperty("content")
    private List<ProductResponse> productResponseList;
}
