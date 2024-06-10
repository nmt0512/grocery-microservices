package com.thieunm.groceryproduct.client.payment;

import com.thieunm.groceryproduct.client.payment.dto.response.GetBestSellingProductQuantityClientResponse;
import com.thieunm.groceryproduct.client.payment.dto.response.GetProductIdListClientResponse;

public interface IPaymentClient {

    GetProductIdListClientResponse getBestSellingProductIdList(int recentDays, int size);

    GetBestSellingProductQuantityClientResponse getBestSellingProductQuantity(int recentDays, int size);

    GetProductIdListClientResponse getRecommendedProductIdList(String accessToken, int recentDays, int size);
}
