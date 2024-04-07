package com.thieunm.grocerybase.cqrs.command;

import com.thieunm.grocerybase.cqrs.ParamHandler;
import com.thieunm.grocerybase.dto.request.CommandRequestData;
import com.thieunm.grocerybase.dto.response.CommandResponseData;

public abstract class ParamCommandHandler<REQUEST extends CommandRequestData, RESPONSE extends CommandResponseData> implements ParamHandler<REQUEST, RESPONSE> {
}
