package com.thieunm.grocerycart.dto.request;

import com.thieunm.grocerybase.dto.request.QueryRequestData;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetAllItemInCartRequest extends QueryRequestData {
}
