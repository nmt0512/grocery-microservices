package com.thieunm.grocerybase.cqrs.query;

import com.thieunm.grocerybase.cqrs.PageSupportHandler;
import com.thieunm.grocerybase.dto.request.QueryRequestData;
import com.thieunm.grocerybase.dto.response.QueryResponseData;

public abstract class PageSupportQueryHandler<REQUEST extends QueryRequestData, RESPONSE extends QueryResponseData> implements PageSupportHandler<REQUEST, RESPONSE> {
}
