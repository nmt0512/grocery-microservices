package com.thieunm.grocerybase.cqrs.command;

import com.thieunm.grocerybase.cqrs.Handler;
import com.thieunm.grocerybase.dto.request.CommandRequestData;
import com.thieunm.grocerybase.dto.response.CommandResponseData;

public abstract class CommandHandler<REQUEST extends CommandRequestData, RESPONSE extends CommandResponseData> implements Handler<REQUEST, RESPONSE> {
}
