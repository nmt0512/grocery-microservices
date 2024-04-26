package com.thieunm.grocerypayment.dto.response.bill;

import com.thieunm.grocerybase.dto.response.QueryResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GetBestSellingProductIdListResponse extends QueryResponseData {
    private List<Integer> productIdList;
}
