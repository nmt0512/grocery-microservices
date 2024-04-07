package com.thieunm.grocerypayment.dto.response.bill;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillItemResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer productId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProductResponse productResponse;
    private Integer quantity;
    private Integer price;
}
