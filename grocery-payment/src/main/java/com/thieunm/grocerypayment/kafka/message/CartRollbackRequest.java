package com.thieunm.grocerypayment.kafka.message;

import com.thieunm.grocerypayment.client.cart.dto.response.InternalCartResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartRollbackRequest {
    private List<InternalCartResponse> internalCartResponseList;
}
