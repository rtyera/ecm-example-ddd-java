package com.tycode.ecm.seller.product.application.event;

import com.tycode.ecm.shared.domain.bus.event.Event;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class DeliveredReviewEvent extends Event {
    private final String author;
    private final String message;

    public DeliveredReviewEvent(){
        super(null);
        this.author = null;
        this.message = null;
    }

    public DeliveredReviewEvent(String aggregateId, String author, String message){
        super(aggregateId);
        this.author = author;
        this.message = message;
    }
    @Override
    public String eventName() {
        return "review:delivered";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("author", author);
            put("message", message);
        }};
    }

    @Override
    public Event fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new DeliveredReviewEvent(
                        aggregateId,
                        (String)body.get("author"),
                        (String)body.get("message")
                    );
    }
}
