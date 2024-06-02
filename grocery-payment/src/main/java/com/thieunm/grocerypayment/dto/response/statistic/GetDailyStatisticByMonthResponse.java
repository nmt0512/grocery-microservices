package com.thieunm.grocerypayment.dto.response.statistic;

import com.thieunm.grocerybase.dto.response.QueryResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetDailyStatisticByMonthResponse extends QueryResponseData {
    private List<StatisticResponse> statisticResponseList;
}
