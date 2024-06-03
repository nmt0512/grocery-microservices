package com.thieunm.grocerypayment.client.product;

import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.client.product.dto.request.DeductQuantityListProductClientRequest;
import com.thieunm.grocerypayment.client.product.dto.request.GetProductByIdListClientRequest;
import com.thieunm.grocerypayment.client.product.dto.response.DeductQuantityListProductClientResponse;
import com.thieunm.grocerypayment.client.product.dto.response.GetProductByIdListClientResponse;
import com.thieunm.grocerypayment.exception.ProductClientException;
import com.thieunm.groceryutils.JsonMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductClient implements IProductClient {

    private static final String DEDUCT_QUANTITY_LIST_PRODUCT_URI = "/deductQuantity";

    private final RestTemplate restTemplate;

    @Value("${grocery.client-uri.grocery-product}")
    private String productClientBaseUri;

    @Override
    public DeductQuantityListProductClientResponse deductQuantityListProduct(DeductQuantityListProductClientRequest request) {
        String deductQuantityListProductUrl = productClientBaseUri + DEDUCT_QUANTITY_LIST_PRODUCT_URI;
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(deductQuantityListProductUrl)
                .build();
        log.info("REQUEST to uri {}", uriComponents.toUri());
        try {
            HttpEntity<DeductQuantityListProductClientRequest> httpEntity = new HttpEntity<>(request, null);
            BaseResponse baseResponse = restTemplate
                    .exchange(uriComponents.toUri(), HttpMethod.PUT, httpEntity, BaseResponse.class)
                    .getBody();
            log.info("RESPONSE from uri {} with body: {}", uriComponents.toUri(), JsonMapperUtil.toString(baseResponse));
            String responseDataJsonStr = JsonMapperUtil.toString(baseResponse.getData());
            return JsonMapperUtil.parseJsonToObject(responseDataJsonStr, DeductQuantityListProductClientResponse.class);
        } catch (Exception exception) {
            throw new ProductClientException("Product Client Error on request to uri " + uriComponents.toUri());
        }
    }

    @Override
    public GetProductByIdListClientResponse getProductByIdList(GetProductByIdListClientRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        String productIdListStr = request.getProductIdList()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(productClientBaseUri)
                .queryParam("idList", productIdListStr)
                .build();
        log.info("REQUEST to uri {}", uriComponents.toUri());
        BaseResponse baseResponse = restTemplate.getForObject(uriComponents.toUri(), BaseResponse.class);
        log.info("RESPONSE from uri {} with body: {}", uriComponents.toUri(), JsonMapperUtil.toString(baseResponse));
        String responseDataJsonStr = JsonMapperUtil.toString(baseResponse.getData());
        return JsonMapperUtil.parseJsonToObject(responseDataJsonStr, GetProductByIdListClientResponse.class);
    }
}
