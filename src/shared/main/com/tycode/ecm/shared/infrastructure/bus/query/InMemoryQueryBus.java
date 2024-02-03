package com.tycode.ecm.shared.infrastructure.bus.query;

import com.tycode.ecm.shared.domain.bus.query.Query;
import com.tycode.ecm.shared.domain.bus.query.QueryBus;
import com.tycode.ecm.shared.domain.bus.query.QueryHandler;
import com.tycode.ecm.shared.domain.bus.query.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public final class InMemoryQueryBus implements QueryBus {
    private final QueryHandlersInformation information;
    private final ApplicationContext       context;

    public InMemoryQueryBus(QueryHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context     = context;
    }

    @Override
    public Response ask(Query query)  {
        Class<? extends QueryHandler> queryHandlerClass = information.search(query.getClass());
        QueryHandler handler = context.getBean(queryHandlerClass);

        return handler.handle(query);

    }
}
