package com.thieunm.grocerypayment.dto.response.bill;

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
    private Integer id;
    private String name;
    private String code;
    private String description;
    private Integer unitPrice;
    private Integer quantity;
    @JsonProperty("images")
    private List<String> imageUrlList;
}
