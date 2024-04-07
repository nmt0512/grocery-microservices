package com.thieunm.grocerycart.controller;

import com.thieunm.grocerybase.cqrs.BaseController;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerycart.dto.request.DeleteAndGetCartByIdListRequest;
import com.thieunm.grocerycart.dto.response.DeleteAndGetCartByIdListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/internal/cart")
public class DeleteAndGetCartByIdListController extends BaseController {

    @DeleteMapping("/deleteAndGet")
    ResponseEntity<BaseResponse<DeleteAndGetCartByIdListResponse>> deleteAndGetCartByIdList(
            @RequestParam("idList") List<String> idList
    ) {
        return execute(new DeleteAndGetCartByIdListRequest(idList), DeleteAndGetCartByIdListResponse.class);
    }
}
