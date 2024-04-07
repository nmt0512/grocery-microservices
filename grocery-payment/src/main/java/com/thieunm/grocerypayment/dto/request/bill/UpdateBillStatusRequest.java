package com.thieunm.grocerypayment.dto.request.bill;

import com.thieunm.grocerybase.dto.request.CommandRequestData;
import com.thieunm.grocerypayment.enums.BillStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBillStatusRequest extends CommandRequestData {
    private int billId;
    private BillStatus billStatus;
}
