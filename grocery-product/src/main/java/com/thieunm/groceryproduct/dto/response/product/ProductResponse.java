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
public class ProductResponse extends QueryResponseData {
    private int id;
    private Integer categoryId;
    private String name;
    private String code;
    private String description;
    private int unitPrice;
    private int quantity;
    @JsonProperty("images")
    private List<String> imageUrlList;
}
