package com.thieunm.groceryproduct.client.payment;

import com.thieunm.groceryproduct.client.payment.dto.response.GetBestSellingProductIdListClientResponse;

public interface IPaymentClient {

    GetBestSellingProductIdListClientResponse getBestSellingProductIdList(int recentDays, int size);
}
