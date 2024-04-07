package com.thieunm.grocerypayment.client.product.dto.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductByIdListClientRequest {
    private List<Integer> productIdList;
}
