package com.thieunm.groceryproduct.client.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBestSellingProductIdListClientResponse {
    private List<Integer> productIdList;
}
