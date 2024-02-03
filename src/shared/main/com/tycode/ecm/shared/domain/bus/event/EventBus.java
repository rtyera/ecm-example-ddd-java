package com.tycode.ecm.shared.domain.bus.event;

import java.util.List;

public interface EventBus {

    void publish(final List<Event> events);
}
