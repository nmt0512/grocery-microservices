package com.thieunm.grocerycart.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    private String id;
    @JsonProperty("product")
    private ProductResponse productResponse;
    private Integer quantity;
}
