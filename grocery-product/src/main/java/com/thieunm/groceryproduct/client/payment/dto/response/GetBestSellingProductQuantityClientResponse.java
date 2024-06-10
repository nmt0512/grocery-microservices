package com.thieunm.groceryproduct.client.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBestSellingProductQuantityClientResponse {
    @JsonProperty("productQuantityResponseList")
    private List<ProductQuantityClientResponse> productQuantityClientResponseList;
}
