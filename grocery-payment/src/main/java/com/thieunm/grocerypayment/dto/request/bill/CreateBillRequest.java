package com.thieunm.grocerypayment.dto.request.bill;

import com.thieunm.grocerybase.dto.request.CommandRequestData;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CreateBillRequest extends CommandRequestData {
    private List<@Size(min = 36, max = 36) String> cartIdList;
    private String pickUpDate;
    private String pickUpTime;
}
