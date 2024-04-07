package com.thieunm.grocerycart.client;

import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerycart.client.dto.request.GetAvailableProductByIdClientRequest;
import com.thieunm.grocerycart.client.dto.request.GetProductByIdListClientRequest;
import com.thieunm.grocerycart.client.dto.response.GetAvailableProductByIdClientResponse;
import com.thieunm.grocerycart.client.dto.response.GetProductByIdListClientResponse;
import com.thieunm.groceryutils.JsonMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductClient implements IProductClient {

    private static final String GET_AVAILABLE_PRODUCT_BY_ID_URI = "/available";

    private final RestClient.Builder restClientBuilder;

    @Value("${grocery.client-uri.grocery-product}")
    private String productClientBaseUri;

    @Override
    public GetAvailableProductByIdClientResponse getAvailableProductById(GetAvailableProductByIdClientRequest request) {
        String getAvailableProductByIdUrl = productClientBaseUri + GET_AVAILABLE_PRODUCT_BY_ID_URI;
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(getAvailableProductByIdUrl)
                .queryParam("productId", request.getProductId())
                .queryParam("quantity", request.getQuantity())
                .build();
        log.info("REQUEST to uri {}", uriComponents.toUri());
        BaseResponse baseResponse = restClientBuilder.build()
                .get()
                .uri(uriComponents.toUri())
                .retrieve()
                .body(BaseResponse.class);
        log.info("RESPONSE from uri {} with body: {}", uriComponents.toUri(), JsonMapperUtil.toString(baseResponse));
        String responseDataJsonStr = JsonMapperUtil.toString(baseResponse.getData());
        return JsonMapperUtil.parseJsonToObject(responseDataJsonStr, GetAvailableProductByIdClientResponse.class);
    }

    @Override
    public GetProductByIdListClientResponse getProductByIdList(GetProductByIdListClientRequest request) {
        String productIdListStr = request.getProductIdList()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(productClientBaseUri)
                .queryParam("idList", productIdListStr)
                .build();
        log.info("REQUEST to uri {}", uriComponents.toUri());
        BaseResponse baseResponse = restClientBuilder.build()
                .get()
                .uri(uriComponents.toUri())
                .retrieve()
                .body(BaseResponse.class);
        log.info("RESPONSE from uri {} with body: {}", uriComponents.toUri(), JsonMapperUtil.toString(baseResponse));
        String responseDataJsonStr = JsonMapperUtil.toString(baseResponse.getData());
        return JsonMapperUtil.parseJsonToObject(responseDataJsonStr, GetProductByIdListClientResponse.class);
    }
}
