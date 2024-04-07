package com.thieunm.grocerypayment.client.product.dto.response;

import com.thieunm.grocerypayment.dto.response.bill.ProductResponse;
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
