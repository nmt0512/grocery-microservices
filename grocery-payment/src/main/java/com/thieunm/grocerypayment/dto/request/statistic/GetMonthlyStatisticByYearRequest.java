package com.thieunm.grocerypayment.dto.request.statistic;

import com.thieunm.grocerybase.dto.request.QueryRequestData;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GetMonthlyStatisticByYearRequest extends QueryRequestData {
    private int year;
}
