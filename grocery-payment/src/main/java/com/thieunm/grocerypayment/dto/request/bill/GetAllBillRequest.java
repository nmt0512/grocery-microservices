package com.thieunm.grocerypayment.dto.request.bill;

import com.thieunm.grocerybase.dto.request.QueryRequestData;
import com.thieunm.grocerypayment.enums.BillStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBillRequest extends QueryRequestData {
    private List<BillStatus> billStatusList;
    private Integer pageNumber;
    private Integer pageSize;
}
