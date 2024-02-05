package com.tycode.ecm.shared.infrastructure;

import com.tycode.ecm.shared.domain.bus.event.Event;
import com.tycode.ecm.shared.domain.bus.event.EventBus;
import com.tycode.ecm.shared.domain.bus.query.Query;
import com.tycode.ecm.shared.domain.bus.query.QueryBus;
import com.tycode.ecm.shared.domain.bus.query.Response;
import org.junit.jupiter.api.BeforeEach;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public abstract class UnitTestCase {
    protected EventBus      eventBus;
    protected QueryBus      queryBus;

    @BeforeEach
    protected void setUp() {
        eventBus      = mock(EventBus.class);
        queryBus      = mock(QueryBus.class);
    }

    public void shouldHavePublished(List<Event> events) {
        verify(eventBus, atLeastOnce()).publish(events);
    }

    public void shouldHavePublished(Event domainEvent) {
        shouldHavePublished(Collections.singletonList(domainEvent));
    }

    public void shouldAsk(Query query, Response response) {
        when(queryBus.ask(query)).thenReturn(response);
    }
}
