package com.thieunm.grocerycart.handler;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerycart.dto.request.UpdateCartQuantityRequest;
import com.thieunm.grocerycart.dto.response.CartResponse;
import com.thieunm.grocerycart.dto.response.UpdateCartQuantityResponse;
import com.thieunm.grocerycart.entity.Cart;
import com.thieunm.grocerycart.repository.CartRepository;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCartQuantityHandler extends CommandHandler<UpdateCartQuantityRequest, UpdateCartQuantityResponse> {

    private final CartRepository cartRepository;

    @Override
    public UpdateCartQuantityResponse handle(UpdateCartQuantityRequest requestData) {
        Cart cart = cartRepository.findById(requestData.getId()).get();
        Integer newTotalPrice = cart.getTotalPrice() / cart.getQuantity() * requestData.getNewQuantity();
        cart.setQuantity(requestData.getNewQuantity());
        cart.setTotalPrice(newTotalPrice);
        cartRepository.save(cart);
        return new UpdateCartQuantityResponse(Mapper.map(cart, CartResponse.class));
    }
}
