package com.thieunm.groceryproduct.client;

import com.thieunm.groceryproduct.client.dto.response.GetBestSellingProductIdListClientResponse;

public interface IPaymentClient {

    GetBestSellingProductIdListClientResponse getBestSellingProductIdList(int recentDays, int size);
}
