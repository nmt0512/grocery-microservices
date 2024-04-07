package com.thieunm.grocerybase.cqrs;

import com.thieunm.grocerybase.cqrs.command.CommandHandler;
import com.thieunm.grocerybase.cqrs.command.ParamCommandHandler;
import com.thieunm.grocerybase.cqrs.query.PageSupportQueryHandler;
import com.thieunm.grocerybase.cqrs.query.ParamQueryHandler;
import com.thieunm.grocerybase.cqrs.query.QueryHandler;
import com.thieunm.grocerybase.dto.request.CommandRequestData;
import com.thieunm.grocerybase.dto.request.QueryRequestData;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Registry {

    private static final Map<Class<? extends CommandRequestData>, CommandHandler> COMMAND_HANDLER_MAP = new HashMap<>();
    private static final Map<Class<? extends QueryRequestData>, QueryHandler> QUERY_HANDLER_MAP = new HashMap<>();
    private static final Map<Class<? extends CommandRequestData>, ParamCommandHandler> PARAM_COMMAND_HANDLER_MAP = new HashMap<>();
    private static final Map<Class<? extends QueryRequestData>, ParamQueryHandler> PARAM_QUERY_HANDLER_MAP = new HashMap<>();
    private static final Map<Class<? extends QueryRequestData>, PageSupportQueryHandler> PAGE_SUPPORT_QUERY_HANDLER_MAP = new HashMap<>();

    private final ApplicationContext applicationContext;

    public Registry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        initCommandHandlerBeans();
        initParamCommandHandlerBeans();
        initQueryHandlerBeans();
        initParamQueryHandlerBeans();
        initPageSupportQueryHandlerBeans();
    }

    private void initCommandHandlerBeans() {
        String[] commandHandlerBeanNames = applicationContext.getBeanNamesForType(CommandHandler.class);
        for (String commandHandlerBeanName : commandHandlerBeanNames) {
            initCommandHandlerBean(commandHandlerBeanName);
        }
    }

    private void initParamCommandHandlerBeans() {
        String[] commandHandlerBeanNames = applicationContext.getBeanNamesForType(ParamCommandHandler.class);
        for (String commandHandlerBeanName : commandHandlerBeanNames) {
            initPramCommandHandlerBean(commandHandlerBeanName);
        }
    }

    private void initQueryHandlerBeans() {
        String[] queryHandlerBeanNames = applicationContext.getBeanNamesForType(QueryHandler.class);
        for (String queryHandlerBeanName : queryHandlerBeanNames) {
            initQueryHandlerBean(queryHandlerBeanName);
        }
    }

    private void initParamQueryHandlerBeans() {
        String[] queryHandlerBeanNames = applicationContext.getBeanNamesForType(ParamQueryHandler.class);
        for (String queryHandlerBeanName : queryHandlerBeanNames) {
            initParamQueryHandlerBean(queryHandlerBeanName);
        }
    }

    private void initPageSupportQueryHandlerBeans() {
        String[] queryHandlerBeanNames = applicationContext.getBeanNamesForType(PageSupportQueryHandler.class);
        for (String queryHandlerBeanName : queryHandlerBeanNames) {
            initPageSupportQueryHandleBean(queryHandlerBeanName);
        }
    }

    private void initCommandHandlerBean(String commandHandlerBeanName) {
        Class<CommandHandler<?, ?>> handlerClass = (Class<CommandHandler<?, ?>>) applicationContext.getType(commandHandlerBeanName);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler.class);
        Class<? extends CommandRequestData> commandType = (Class<? extends CommandRequestData>) generics[0];

        COMMAND_HANDLER_MAP.put(commandType, (CommandHandler) applicationContext.getBean(commandHandlerBeanName));
    }

    private void initPramCommandHandlerBean(String commandHandlerBeanName) {
        Class<ParamCommandHandler<?, ?>> handlerClass = (Class<ParamCommandHandler<?, ?>>) applicationContext.getType(commandHandlerBeanName);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, ParamCommandHandler.class);
        Class<? extends CommandRequestData> commandType = (Class<? extends CommandRequestData>) generics[0];
        PARAM_COMMAND_HANDLER_MAP.put(commandType, (ParamCommandHandler) applicationContext.getBean(commandHandlerBeanName));
    }

    private void initQueryHandlerBean(String queryHandlerBeanName) {
        Class<QueryHandler<?, ?>> handlerClass = (Class<QueryHandler<?, ?>>) applicationContext.getType(queryHandlerBeanName);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, QueryHandler.class);
        Class<? extends QueryRequestData> queryType = (Class<? extends QueryRequestData>) generics[0];

        QUERY_HANDLER_MAP.put(queryType, (QueryHandler) applicationContext.getBean(queryHandlerBeanName));
    }

    private void initParamQueryHandlerBean(String queryHandlerBeanName) {
        Class<ParamQueryHandler<?, ?>> handlerClass = (Class<ParamQueryHandler<?, ?>>) applicationContext.getType(queryHandlerBeanName);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, ParamQueryHandler.class);
        Class<? extends QueryRequestData> queryType = (Class<? extends QueryRequestData>) generics[0];

        PARAM_QUERY_HANDLER_MAP.put(queryType, (ParamQueryHandler) applicationContext.getBean(queryHandlerBeanName));
    }

    private void initPageSupportQueryHandleBean(String pageSupportQueryHandlerBeanName) {
        Class<PageSupportQueryHandler<?, ?>> handlerClass = (Class<PageSupportQueryHandler<?, ?>>) applicationContext.getType(pageSupportQueryHandlerBeanName);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, PageSupportQueryHandler.class);
        Class<? extends QueryRequestData> queryType = (Class<? extends QueryRequestData>) generics[0];
        PAGE_SUPPORT_QUERY_HANDLER_MAP.put(queryType, (PageSupportQueryHandler) applicationContext.getBean(pageSupportQueryHandlerBeanName));
    }

    public <REQUEST extends CommandRequestData> CommandHandler getCommandHandler(Class<REQUEST> requestCommandClass) {
        CommandHandler commandHandler = COMMAND_HANDLER_MAP.get(requestCommandClass);
        return commandHandler;
    }

    public <REQUEST extends CommandRequestData> ParamCommandHandler getCommandHandler(Class<REQUEST> requestCommandClass, Object... args) {
        ParamCommandHandler commandHandler = PARAM_COMMAND_HANDLER_MAP.get(requestCommandClass);
        return commandHandler;
    }

    public <REQUEST extends QueryRequestData> QueryHandler getQueryHandler(Class<REQUEST> requestQueryClass) {
        QueryHandler queryHandler = QUERY_HANDLER_MAP.get(requestQueryClass);
        return queryHandler;
    }

    public <REQUEST extends QueryRequestData> ParamQueryHandler getQueryHandler(Class<REQUEST> requestQueryClass, Object... args) {
        ParamQueryHandler queryHandler = PARAM_QUERY_HANDLER_MAP.get(requestQueryClass);
        return queryHandler;
    }

    public <REQUEST extends QueryRequestData> PageSupportHandler getPageSupportQueryHandler(Class<REQUEST> requestQueryClass) {
        PageSupportHandler pageSupportQueryHandler = PAGE_SUPPORT_QUERY_HANDLER_MAP.get(requestQueryClass);
        if (pageSupportQueryHandler == null) {
            return null;
        }
        return pageSupportQueryHandler;
    }

}
