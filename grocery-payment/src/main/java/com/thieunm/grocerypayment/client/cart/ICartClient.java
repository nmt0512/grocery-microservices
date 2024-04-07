package com.thieunm.grocerypayment.client.cart;

import com.thieunm.grocerypayment.client.cart.dto.request.DeleteAndGetCartByIdListClientRequest;
import com.thieunm.grocerypayment.client.cart.dto.response.DeleteAndGetCartByIdListClientResponse;

public interface ICartClient {

    DeleteAndGetCartByIdListClientResponse deleteAndGetCartByIdList(DeleteAndGetCartByIdListClientRequest request);
}
