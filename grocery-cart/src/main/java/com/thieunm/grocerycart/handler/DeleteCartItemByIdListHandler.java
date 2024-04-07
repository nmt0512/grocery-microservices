package com.thieunm.grocerycart.handler;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerycart.dto.request.DeleteCartItemByIdListRequest;
import com.thieunm.grocerycart.dto.response.DeleteCartItemByIdListResponse;
import com.thieunm.grocerycart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCartItemByIdListHandler extends CommandHandler<DeleteCartItemByIdListRequest, DeleteCartItemByIdListResponse> {

    private final CartRepository cartRepository;

    @Override
    public DeleteCartItemByIdListResponse handle(DeleteCartItemByIdListRequest requestData) {
        cartRepository.deleteAllById(requestData.getIdList());
        return new DeleteCartItemByIdListResponse(requestData.getIdList());
    }
}
