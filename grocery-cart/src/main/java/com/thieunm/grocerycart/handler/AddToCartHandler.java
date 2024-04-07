package com.thieunm.grocerycart.handler;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerybase.exception.BaseException;
import com.thieunm.grocerybase.exception.CommonErrorCode;
import com.thieunm.grocerycart.client.IProductClient;
import com.thieunm.grocerycart.client.dto.request.GetAvailableProductByIdClientRequest;
import com.thieunm.grocerycart.client.dto.response.GetAvailableProductByIdClientResponse;
import com.thieunm.grocerycart.dto.request.AddToCartRequest;
import com.thieunm.grocerycart.dto.response.AddToCartResponse;
import com.thieunm.grocerycart.dto.response.ProductResponse;
import com.thieunm.grocerycart.entity.Cart;
import com.thieunm.grocerycart.repository.CartRepository;
import com.thieunm.groceryutils.JsonWebTokenUtil;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddToCartHandler extends CommandHandler<AddToCartRequest, AddToCartResponse> {

    private final CartRepository cartRepository;
    private final IProductClient productClient;

    @Override
    public AddToCartResponse handle(AddToCartRequest requestData) {
        String userId = JsonWebTokenUtil.getUserId(requestData.getAccessToken());
        if (userId != null) {
            Optional<Cart> optionalOldCart = cartRepository.findByUserIdAndProductId(userId, requestData.getProductId());
            if (optionalOldCart.isEmpty()) {
                GetAvailableProductByIdClientRequest clientRequest = GetAvailableProductByIdClientRequest
                        .builder()
                        .productId(requestData.getProductId())
                        .quantity(requestData.getQuantity())
                        .build();
                GetAvailableProductByIdClientResponse clientResponse = productClient.getAvailableProductById(clientRequest);
                if (clientResponse.isAvailable()) {
                    ProductResponse productResponse = clientResponse.getProduct();
                    Integer totalPrice = productResponse.getUnitPrice() * requestData.getQuantity();
                    Cart cart = Cart.builder()
                            .id(UUID.randomUUID().toString())
                            .productId(requestData.getProductId())
                            .userId(userId)
                            .totalPrice(totalPrice)
                            .quantity(requestData.getQuantity())
                            .build();
                    cart = cartRepository.save(cart);
                    return Mapper.map(cart, AddToCartResponse.class);
                } else {
                    throw new BaseException(CommonErrorCode.PRODUCT_OUT_OF_STOCK);
                }
            } else {
                Cart updatingCart = optionalOldCart.get();
                int newCartQuantity = updatingCart.getQuantity() + requestData.getQuantity();

                GetAvailableProductByIdClientRequest clientRequest = GetAvailableProductByIdClientRequest
                        .builder()
                        .productId(requestData.getProductId())
                        .quantity(newCartQuantity)
                        .build();
                GetAvailableProductByIdClientResponse clientResponse = productClient.getAvailableProductById(clientRequest);

                if (clientResponse.isAvailable()) {
                    int newCartTotalPrice = updatingCart.getTotalPrice() / updatingCart.getQuantity() * newCartQuantity;
                    updatingCart.setQuantity(newCartQuantity);
                    updatingCart.setTotalPrice(newCartTotalPrice);
                    cartRepository.save(updatingCart);
                    return Mapper.map(updatingCart, AddToCartResponse.class);
                } else {
                    throw new BaseException(CommonErrorCode.PRODUCT_OUT_OF_STOCK);
                }
            }
        }
        return null;
    }
}
