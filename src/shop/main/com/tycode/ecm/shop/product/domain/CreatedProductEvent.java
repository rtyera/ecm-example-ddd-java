package com.tycode.ecm.shop.product.domain;

import com.tycode.ecm.shared.domain.bus.event.Event;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class CreatedProductEvent extends Event {
    private final String name;
    private final String price;
    private final List<String> images;
    private final String stockQuantity;

    public CreatedProductEvent(){
        super(null);
        this.name = null;
        this.price = null;
        this.images = null;
        this.stockQuantity = null;
    }

    public CreatedProductEvent(String id, String name, float price, List<String> images, int stockQuantity){
        super(id);
        this.name = name;
        this.price = Float.toString(price);
        this.images = images;
        this.stockQuantity = Integer.toString(stockQuantity);
    }
    @Override
    public String eventName() {
        return "created:product";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("name", name);
            put("price", Float.parseFloat(price));
            put("images", (Serializable) images);
            put("stockQuantity", Integer.parseInt(stockQuantity));
        }};
    }

    @Override
    public Event fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new CreatedProductEvent(
                        aggregateId,
                        (String)body.get("name"),
                        (float)body.get("price"),
                        (List<String>) body.get("images"),
                        (int)body.get("stockQuantity")
                    );
    }
}
