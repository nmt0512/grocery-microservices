package com.thieunm.grocerycart.controller;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerycart.dto.request.DeleteCartItemByIdListRequest;
import com.thieunm.grocerycart.dto.response.DeleteCartItemByIdListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class DeleteCartItemByIdListController extends BaseController {

    @DeleteMapping
    ResponseEntity<BaseResponse<DeleteCartItemByIdListResponse>> deleteCartItemByIdList(
            @RequestParam("idList") List<String> idList
    ) {
        return execute(new DeleteCartItemByIdListRequest(idList), DeleteCartItemByIdListResponse.class);
    }
}
