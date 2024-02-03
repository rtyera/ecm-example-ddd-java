package com.tycode.ecm.shared.infrastructure.bus;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.event.EventSubscriber;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

@Service
public final class EventSubscribersInformation {
    HashMap<Class<?>, EventSubscriberInformation> information;

    public EventSubscribersInformation(HashMap<Class<?>, EventSubscriberInformation> information) {
        this.information = information;
    }

    public EventSubscribersInformation() {
        this(scanDomainEventSubscribers());
    }

    private static HashMap<Class<?>, EventSubscriberInformation> scanDomainEventSubscribers() {
        Reflections   reflections = new Reflections("com.tycode.ecm");
        Set<Class<?>> subscribers = reflections.getTypesAnnotatedWith(EventSubscriber.class);

        HashMap<Class<?>, EventSubscriberInformation> subscribersInformation = new HashMap<>();

        for (Class<?> subscriberClass : subscribers) {
            EventSubscriber annotation = subscriberClass.getAnnotation(EventSubscriber.class);

            subscribersInformation.put(
                subscriberClass,
                new EventSubscriberInformation(subscriberClass, Arrays.asList(annotation.value()))
            );
        }

        return subscribersInformation;
    }

    public Collection<EventSubscriberInformation> all() {
        return information.values();
    }

    public String[] rabbitMqFormattedNames() {
        return information.values()
                          .stream()
                          .map(EventSubscriberInformation::formatRabbitMqQueueName)
                          .distinct()
                          .toArray(String[]::new);
    }
}
