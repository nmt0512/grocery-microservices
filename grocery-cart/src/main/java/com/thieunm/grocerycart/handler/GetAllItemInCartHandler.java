package com.thieunm.grocerycart.handler;

import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerycart.client.IProductClient;
import com.thieunm.grocerycart.client.dto.request.GetProductByIdListClientRequest;
import com.thieunm.grocerycart.client.dto.response.GetProductByIdListClientResponse;
import com.thieunm.grocerycart.dto.request.GetAllItemInCartRequest;
import com.thieunm.grocerycart.dto.response.CartResponse;
import com.thieunm.grocerycart.dto.response.GetAllItemInCartResponse;
import com.thieunm.grocerycart.dto.response.ProductResponse;
import com.thieunm.grocerycart.entity.Cart;
import com.thieunm.grocerycart.repository.CartRepository;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetAllItemInCartHandler extends QueryHandler<GetAllItemInCartRequest, GetAllItemInCartResponse> {

    private final CartRepository cartRepository;
    private final IProductClient productClient;

    @Override
    public GetAllItemInCartResponse handle(GetAllItemInCartRequest requestData) {
        String userId = JsonWebTokenUtil.getUserId(requestData.getAccessToken());
        if (userId != null) {
            List<Cart> userCartList = cartRepository.findByUserId(
                    userId,
                    Sort.by(Sort.Direction.DESC, "createdDate")
            );
            List<Integer> productIdList = userCartList
                    .stream()
                    .map(Cart::getProductId)
                    .toList();
            GetProductByIdListClientRequest clientRequest = new GetProductByIdListClientRequest(productIdList);
            GetProductByIdListClientResponse clientResponse = productClient.getProductByIdList(clientRequest);
            List<CartResponse> cartResponseList = userCartList
                    .stream()
                    .map(cart -> {
                        ProductResponse productResponse = clientResponse.getProductResponseList()
                                .stream()
                                .filter(product -> Objects.equals(product.getId(), cart.getProductId()))
                                .findFirst()
                                .get();
                        return new CartResponse(cart.getId(), productResponse, cart.getQuantity());
                    })
                    .toList();
            return new GetAllItemInCartResponse(cartResponseList);
        }
        return null;
    }
}
