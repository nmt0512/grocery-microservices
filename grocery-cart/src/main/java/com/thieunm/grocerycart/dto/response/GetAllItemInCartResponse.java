package com.thieunm.grocerycart.dto.response;

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
public class GetAllItemInCartResponse extends QueryResponseData {
    private List<CartResponse> cartResponseList;
}
