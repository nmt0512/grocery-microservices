package com.thieunm.groceryproduct.client.payment;

import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.groceryproduct.client.payment.dto.response.GetBestSellingProductQuantityClientResponse;
import com.thieunm.groceryproduct.client.payment.dto.response.GetProductIdListClientResponse;
import com.thieunm.groceryutils.JsonMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentClient implements IPaymentClient {

    private static final String GET_BEST_SELLING_PRODUCT_ID_LIST_URI = "/bestSellingProduct";
    private static final String GET_BEST_SELLING_PRODUCT_QUANTITY_URI = "/bestSellingProductQuantity";
    private static final String GET_RECOMMENDED_PRODUCT_ID_LIST_URI = "/recommendedProduct";

    private final RestClient.Builder restClientBuilder;

    @Value("${grocery.client-uri.grocery-payment}")
    private String paymentClientBaseUri;

    @Override
    public GetProductIdListClientResponse getBestSellingProductIdList(int recentDays, int size) {
        String getBestSellingProductIdListUrl = paymentClientBaseUri + GET_BEST_SELLING_PRODUCT_ID_LIST_URI;
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(getBestSellingProductIdListUrl)
                .queryParam("recentDays", recentDays)
                .queryParam("size", size)
                .build();
        log.info("REQUEST to uri {}", uriComponents.toUri());
        BaseResponse baseResponse = restClientBuilder.build()
                .get()
                .uri(uriComponents.toUri())
                .retrieve()
                .body(BaseResponse.class);
        log.info("RESPONSE from uri {} with body: {}", uriComponents.toUri(), JsonMapperUtil.toString(baseResponse));
        String responseDataJsonStr = JsonMapperUtil.toString(baseResponse.getData());
        return JsonMapperUtil.parseJsonToObject(responseDataJsonStr, GetProductIdListClientResponse.class);
    }

    @Override
    public GetBestSellingProductQuantityClientResponse getBestSellingProductQuantity(int recentDays, int size) {
        String getBestSellingProductIdListUrl = paymentClientBaseUri + GET_BEST_SELLING_PRODUCT_QUANTITY_URI;
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(getBestSellingProductIdListUrl)
                .queryParam("recentDays", recentDays)
                .queryParam("size", size)
                .build();
        log.info("REQUEST to uri {}", uriComponents.toUri());
        BaseResponse baseResponse = restClientBuilder.build()
                .get()
                .uri(uriComponents.toUri())
                .retrieve()
                .body(BaseResponse.class);
        log.info("RESPONSE from uri {} with body: {}", uriComponents.toUri(), JsonMapperUtil.toString(baseResponse));
        String responseDataJsonStr = JsonMapperUtil.toString(baseResponse.getData());
        return JsonMapperUtil.parseJsonToObject(responseDataJsonStr, GetBestSellingProductQuantityClientResponse.class);
    }

    @Override
    public GetProductIdListClientResponse getRecommendedProductIdList(String accessToken, int recentDays, int size) {
        String getBestSellingProductIdListUrl = paymentClientBaseUri + GET_RECOMMENDED_PRODUCT_ID_LIST_URI;
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(getBestSellingProductIdListUrl)
                .queryParam("recentDays", recentDays)
                .queryParam("size", size)
                .build();
        log.info("REQUEST to uri {}", uriComponents.toUri());
        BaseResponse baseResponse = restClientBuilder.build()
                .get()
                .uri(uriComponents.toUri())
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .retrieve()
                .body(BaseResponse.class);
        log.info("RESPONSE from uri {} with body: {}", uriComponents.toUri(), JsonMapperUtil.toString(baseResponse));
        String responseDataJsonStr = JsonMapperUtil.toString(baseResponse.getData());
        return JsonMapperUtil.parseJsonToObject(responseDataJsonStr, GetProductIdListClientResponse.class);
    }

}
