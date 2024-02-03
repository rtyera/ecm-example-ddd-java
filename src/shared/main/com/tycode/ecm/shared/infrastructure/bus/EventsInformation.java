package com.tycode.ecm.shared.infrastructure.bus;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.event.Event;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public final class EventsInformation {
    HashMap<String, Class<? extends Event>> indexedEvents;

    public EventsInformation() {
        Reflections reflections = new Reflections("com.tycode.ecm");
        Set<Class<? extends Event>> classes = reflections.getSubTypesOf(Event.class);

        try {
            indexedEvents = formatEvents(classes);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Class<? extends Event> forName(String name) {
        return indexedEvents.get(name);
    }

    public String forClass(Class<? extends Event> eventClass) {
        return indexedEvents.entrySet()
                                  .stream()
                                  .filter(entry -> Objects.equals(entry.getValue(), eventClass))
                                  .map(Map.Entry::getKey)
                                  .findFirst().orElse("");
    }

    private HashMap<String, Class<? extends Event>> formatEvents(
        Set<Class<? extends Event>> domainEvents
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        HashMap<String, Class<? extends Event>> events = new HashMap<>();

        for (Class<? extends Event> domainEvent : domainEvents) {
            Event nullInstance = domainEvent.getConstructor().newInstance();

            events.put((String) domainEvent.getMethod("eventName").invoke(nullInstance), domainEvent);
        }

        return events;
    }
}
