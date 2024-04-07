package com.thieunm.grocerybase.cqrs;

import com.thieunm.grocerybase.dto.request.RequestData;
import com.thieunm.grocerybase.dto.response.ResponseData;

public interface Handler<REQUEST extends RequestData, RESPONSE extends ResponseData> {
    RESPONSE handle(REQUEST requestData);
}
