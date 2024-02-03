package com.tycode.ecm.shared.infrastructure.bus;

import com.tycode.ecm.shared.domain.Utils;
import com.tycode.ecm.shared.domain.bus.event.Event;

import java.io.Serializable;
import java.util.HashMap;

public final class EventJsonSerializer {
    public static String serialize(Event event) {
        HashMap<String, Serializable> attributes = event.toPrimitives();
        attributes.put("id", event.aggregateId());

        return Utils.jsonEncode(new HashMap<String, Serializable>() {{
            put("data", new HashMap<String, Serializable>() {{
                put("id", event.eventId());
                put("type", event.eventName());
                put("occurred_on", event.occurredOn());
                put("attributes", attributes);
            }});
            put("meta", new HashMap<String, Serializable>());
        }});
    }
}
