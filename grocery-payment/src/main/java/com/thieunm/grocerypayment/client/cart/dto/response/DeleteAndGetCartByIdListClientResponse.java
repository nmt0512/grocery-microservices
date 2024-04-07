package com.thieunm.grocerypayment.client.cart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteAndGetCartByIdListClientResponse {
    private List<InternalCartResponse> internalCartResponseList;
}
