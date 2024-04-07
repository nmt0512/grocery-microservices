package com.thieunm.grocerypayment.client.product;

import com.thieunm.grocerypayment.client.product.dto.request.DeductQuantityListProductClientRequest;
import com.thieunm.grocerypayment.client.product.dto.request.GetProductByIdListClientRequest;
import com.thieunm.grocerypayment.client.product.dto.response.DeductQuantityListProductClientResponse;
import com.thieunm.grocerypayment.client.product.dto.response.GetProductByIdListClientResponse;

public interface IProductClient {

    DeductQuantityListProductClientResponse deductQuantityListProduct(DeductQuantityListProductClientRequest request);

    GetProductByIdListClientResponse getProductByIdList(GetProductByIdListClientRequest request);
}
