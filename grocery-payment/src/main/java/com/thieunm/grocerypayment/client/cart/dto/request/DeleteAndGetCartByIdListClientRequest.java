package com.thieunm.grocerypayment.client.cart.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteAndGetCartByIdListClientRequest {
    private List<String> cartIdList;
}
