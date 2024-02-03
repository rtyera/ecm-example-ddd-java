package com.tycode.ecm.shop.review.application.event;

import com.tycode.ecm.shared.domain.bus.event.Event;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@Getter
public class CreateReviewEvent extends Event {
    private final String productId;
    private final String author;
    private final String message;
    private final String createOn;

    public CreateReviewEvent(){
        super(null);
        this.productId = null;
        this.author = null;
        this.message = null;
        this.createOn = null;
    }

    public CreateReviewEvent(String aggregateId, String productId, String author, String message, String createOn){
        super(aggregateId);
        this.productId = productId;
        this.author = author;
        this.message = message;
        this.createOn = createOn;
    }
    @Override
    public String eventName() {
        return "review:create";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("productId", productId);
            put("author", author);
            put("message", message);
            put("createOn", createOn);
        }};
    }

    @Override
    public Event fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new CreateReviewEvent(
                        aggregateId,
                        (String) body.get("productId"),
                        (String)body.get("author"),
                        (String)body.get("message"),
                        (String) body.get("createOn")
                    );
    }
}
