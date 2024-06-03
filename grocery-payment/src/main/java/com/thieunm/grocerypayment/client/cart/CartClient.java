package com.thieunm.grocerypayment.client.cart;

import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerypayment.client.cart.dto.request.DeleteAndGetCartByIdListClientRequest;
import com.thieunm.grocerypayment.client.cart.dto.response.DeleteAndGetCartByIdListClientResponse;
import com.thieunm.grocerypayment.exception.CartClientException;
import com.thieunm.groceryutils.JsonMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
@RequiredArgsConstructor
public class CartClient implements ICartClient {

    private static final String DELETE_AND_GET_CART_BY_ID_LIST_URI = "/deleteAndGet";

    private final RestClient.Builder restClientBuilder;

    @Value("${grocery.client-uri.grocery-cart}")
    private String cartClientBaseUri;

    @Override
    public DeleteAndGetCartByIdListClientResponse deleteAndGetCartByIdList(DeleteAndGetCartByIdListClientRequest request) {
        String deleteAndGetCartByIdListUrl = cartClientBaseUri + DELETE_AND_GET_CART_BY_ID_LIST_URI;
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(deleteAndGetCartByIdListUrl)
                .queryParam("idList", request.getCartIdList())
                .build();
        log.info("REQUEST to uri {}", uriComponents.toUri());
        try {
            BaseResponse baseResponse = restClientBuilder.build()
                    .delete()
                    .uri(uriComponents.toUri())
                    .retrieve()
                    .body(BaseResponse.class);
            log.info("RESPONSE from uri {} with body: {}", uriComponents.toUri(), JsonMapperUtil.toString(baseResponse));
            String responseDataJsonStr = JsonMapperUtil.toString(baseResponse.getData());
            return JsonMapperUtil.parseJsonToObject(responseDataJsonStr, DeleteAndGetCartByIdListClientResponse.class);
        } catch (Exception exception) {
            throw new CartClientException("Cart Client Error on request to uri " + uriComponents.toUri());
        }
    }
}
