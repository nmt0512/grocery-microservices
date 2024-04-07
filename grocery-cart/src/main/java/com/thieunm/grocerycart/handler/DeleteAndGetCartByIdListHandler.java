package com.thieunm.grocerycart.handler;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerycart.dto.request.DeleteAndGetCartByIdListRequest;
import com.thieunm.grocerycart.dto.response.DeleteAndGetCartByIdListResponse;
import com.thieunm.grocerycart.dto.response.InternalCartResponse;
import com.thieunm.grocerycart.entity.Cart;
import com.thieunm.grocerycart.repository.CartRepository;
import com.thieunm.groceryutils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteAndGetCartByIdListHandler extends CommandHandler<DeleteAndGetCartByIdListRequest, DeleteAndGetCartByIdListResponse> {

    private final CartRepository cartRepository;

    @Override
    @Transactional
    public DeleteAndGetCartByIdListResponse handle(DeleteAndGetCartByIdListRequest requestData) {
        List<Cart> cartList = cartRepository.findAllById(requestData.getIdList());
        List<InternalCartResponse> cartResponseList = Mapper.mapList(cartList, InternalCartResponse.class);
        cartRepository.deleteAllById(requestData.getIdList());
        return new DeleteAndGetCartByIdListResponse(cartResponseList);
    }
}
