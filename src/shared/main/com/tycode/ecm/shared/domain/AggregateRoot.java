package com.tycode.ecm.shared.domain;


import com.tycode.ecm.shared.domain.bus.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public abstract class AggregateRoot {

    private List<Event> events = new ArrayList<>();

    final public List<Event> pullDomainEvents() {
        List<Event> e = events;
        events = Collections.emptyList();

        return e;
    }

    final protected void record(Event event) {
        events.add(event);
    }
}