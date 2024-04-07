package com.thieunm.grocerybase.cqrs;

import com.thieunm.grocerybase.cqrs.query.PageSupport;
import com.thieunm.grocerybase.dto.request.RequestData;
import com.thieunm.grocerybase.dto.response.BaseResponse;
import com.thieunm.grocerybase.dto.response.ResponseData;
import com.thieunm.grocerybase.dto.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class BaseController {

    @Autowired
    protected SpringBus springBus;

    @Autowired
    protected ResponseFactory responseFactory;

    protected <REQUEST extends RequestData, RESPONSE extends ResponseData> ResponseEntity<BaseResponse<RESPONSE>> execute(REQUEST requestData, Class<RESPONSE> responseClass) {
        return responseFactory.success(springBus.execute(requestData, responseClass));
    }

    protected <REQUEST extends RequestData, RESPONSE extends ResponseData> ResponseEntity<BaseResponse<RESPONSE>> execute(
            REQUEST requestData,
            Class<RESPONSE> responseClass,
            Object... args) {
        return responseFactory.success(springBus.execute(requestData, responseClass, args));
    }

    protected <REQUEST extends RequestData, RESPONSE extends ResponseData> ResponseEntity<BaseResponse<PageSupport<RESPONSE>>> executePagination(
            REQUEST requestData,
            Class<RESPONSE> responseClass) {
        return responseFactory.success(springBus.executePagination(requestData, responseClass));
    }

}
