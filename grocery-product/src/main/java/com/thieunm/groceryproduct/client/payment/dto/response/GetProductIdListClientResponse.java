package com.thieunm.groceryproduct.client.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductIdListClientResponse {
    private List<Integer> productIdList;
}
