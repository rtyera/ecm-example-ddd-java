package com.tycode.ecm.shared.infrastructure.bus;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.Utils;
import com.tycode.ecm.shared.domain.bus.event.Event;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Service
@AllArgsConstructor
public final class EventJsonDeserializer {

    private final EventsInformation information;

    public Event deserialize(String body) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        System.out.println(body);
        HashMap<String, Serializable> eventData        = Utils.jsonDecode(body);
        HashMap<String, Serializable> data             = (HashMap<String, Serializable>) eventData.get("data");
        HashMap<String, Serializable> attributes       = (HashMap<String, Serializable>) data.get("attributes");
        Class<? extends Event>  eventClass = information.forName((String) data.get("type"));

        Event nullInstance = eventClass.getConstructor().newInstance();

        Method fromPrimitivesMethod = eventClass.getMethod(
            "fromPrimitives",
            String.class,
            HashMap.class,
            String.class,
            String.class
        );

        Object domainEvent = fromPrimitivesMethod.invoke(
            nullInstance,
            attributes.get("id").toString(),
            attributes,
            data.get("id").toString(),
            data.get("occurred_on").toString()
        );

        return (Event) domainEvent;
    }
}
