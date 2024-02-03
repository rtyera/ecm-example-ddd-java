package com.tycode.ecm.shared.infrastructure.bus;

import com.tycode.ecm.shared.domain.Utils;
import com.tycode.ecm.shared.domain.bus.event.Event;

import java.util.List;

public record EventSubscriberInformation(Class<?> subscriberClass, List<Class<? extends Event>> subscribedEvents) {

    public String contextName() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[2];
    }

    public String moduleName() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[3];
    }

    public String className() {
        String[] nameParts = subscriberClass.getName().split("\\.");
        return nameParts[nameParts.length - 1];
    }

    public String formatRabbitMqQueueName() {
        return String.format("ecm.%s.%s.%s", contextName(), moduleName(), Utils.toSnake(className()));
    }
}