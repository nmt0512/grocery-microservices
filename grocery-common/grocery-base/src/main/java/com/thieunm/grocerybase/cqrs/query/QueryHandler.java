package com.thieunm.grocerybase.cqrs.query;

import com.thieunm.grocerybase.cqrs.Handler;
import com.thieunm.grocerybase.dto.request.QueryRequestData;
import com.thieunm.grocerybase.dto.response.QueryResponseData;

public abstract class QueryHandler<REQUEST extends QueryRequestData, RESPONSE extends QueryResponseData> implements Handler<REQUEST, RESPONSE> {
}
