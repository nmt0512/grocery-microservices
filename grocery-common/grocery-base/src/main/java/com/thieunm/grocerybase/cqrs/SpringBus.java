package com.thieunm.grocerybase.cqrs;

import com.thieunm.grocerybase.cqrs.query.PageSupport;
import com.thieunm.grocerybase.dto.request.CommandRequestData;
import com.thieunm.grocerybase.dto.request.QueryRequestData;
import com.thieunm.grocerybase.dto.request.RequestData;
import com.thieunm.grocerybase.dto.response.ResponseData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class SpringBus implements CqrsBus {

    private final Registry registry;

    @Override
    public <REQUEST extends RequestData, RESPONSE extends ResponseData> RESPONSE execute(REQUEST requestData, Class<RESPONSE> responseClass) {
        Handler handler = getHandler(requestData);
        if (handler == null) {
            return null;
        }
        return (RESPONSE) handler.handle(requestData);
    }

    @Override
    public <REQUEST extends RequestData, RESPONSE extends ResponseData> RESPONSE execute(REQUEST requestData, Class<RESPONSE> responseClass, Object... args) {
        ParamHandler handler = getHandler(requestData, args);
        if (handler == null) {
            return null;
        }
        return (RESPONSE) handler.handle(requestData, args);
    }

    @Override
    public <REQUEST extends RequestData, RESPONSE extends ResponseData> PageSupport<RESPONSE> executePagination(REQUEST requestData, Class<RESPONSE> responseClass) {
        PageSupportHandler pageSupportHandler = getPageSupportHandler(requestData);
        if (Objects.isNull(pageSupportHandler)) {
            return null;
        }
        return pageSupportHandler.handle(requestData);
    }

    private <REQUEST extends RequestData> Handler getHandler(REQUEST requestData) {
        if (requestData instanceof CommandRequestData) {
            return registry.getCommandHandler(((CommandRequestData) requestData).getClass());
        }
        if (requestData instanceof QueryRequestData) {
            return registry.getQueryHandler(((QueryRequestData) requestData).getClass());
        }
        return null;
    }

    private <REQUEST extends RequestData> ParamHandler getHandler(REQUEST requestData, Object... args) {
        if (requestData instanceof CommandRequestData) {
            return registry.getCommandHandler(((CommandRequestData) requestData).getClass(), args);
        }
        if (requestData instanceof QueryRequestData) {
            return registry.getQueryHandler(((QueryRequestData) requestData).getClass(), args);
        }
        return null;
    }

    private <REQUEST extends RequestData> PageSupportHandler getPageSupportHandler(REQUEST requestData) {
        if (requestData instanceof QueryRequestData) {
            return registry.getPageSupportQueryHandler(((QueryRequestData) requestData).getClass());
        }
        return null;
    }

}
