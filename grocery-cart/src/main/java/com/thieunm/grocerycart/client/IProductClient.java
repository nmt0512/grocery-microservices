package com.thieunm.grocerycart.client;

import com.thieunm.grocerycart.client.dto.request.GetAvailableProductByIdClientRequest;
import com.thieunm.grocerycart.client.dto.request.GetProductByIdListClientRequest;
import com.thieunm.grocerycart.client.dto.response.GetAvailableProductByIdClientResponse;
import com.thieunm.grocerycart.client.dto.response.GetProductByIdListClientResponse;

public interface IProductClient {

    GetAvailableProductByIdClientResponse getAvailableProductById(GetAvailableProductByIdClientRequest request);

    GetProductByIdListClientResponse getProductByIdList(GetProductByIdListClientRequest request);
}
