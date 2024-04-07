package com.thieunm.grocerycart.client.dto.response;

import com.thieunm.grocerycart.dto.response.ProductResponse;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductByIdListClientResponse {
    private List<ProductResponse> productResponseList;
}
