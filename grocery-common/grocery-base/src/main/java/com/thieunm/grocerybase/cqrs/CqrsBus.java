package com.thieunm.grocerybase.cqrs;

import com.thieunm.grocerybase.cqrs.query.PageSupport;
import com.thieunm.grocerybase.dto.request.RequestData;
import com.thieunm.grocerybase.dto.response.ResponseData;

public interface CqrsBus {

    <REQUEST extends RequestData, RESPONSE extends ResponseData> RESPONSE execute(REQUEST requestData, Class<RESPONSE> responseClass);

    <REQUEST extends RequestData, RESPONSE extends ResponseData> RESPONSE execute(REQUEST requestData, Class<RESPONSE> responseClass, Object... args);

    <REQUEST extends RequestData, RESPONSE extends ResponseData> PageSupport<RESPONSE> executePagination(REQUEST requestData, Class<RESPONSE> responseClass);

}
