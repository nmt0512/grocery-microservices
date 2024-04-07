package com.thieunm.grocerybase.cqrs;

import com.thieunm.grocerybase.cqrs.query.PageSupport;
import com.thieunm.grocerybase.dto.request.RequestData;
import com.thieunm.grocerybase.dto.response.ResponseData;

public interface ParamPageSupportHandler<REQUEST extends RequestData, RESPONSE extends ResponseData> {
    PageSupport<RESPONSE> handle(REQUEST requestData, Object... args);
}
