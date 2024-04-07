package com.thieunm.grocerybase.cqrs.query;

import com.thieunm.grocerybase.cqrs.ParamHandler;
import com.thieunm.grocerybase.dto.request.QueryRequestData;
import com.thieunm.grocerybase.dto.response.QueryResponseData;

public abstract class ParamQueryHandler<REQUEST extends QueryRequestData, RESPONSE extends QueryResponseData> implements ParamHandler<REQUEST, RESPONSE> {
}
