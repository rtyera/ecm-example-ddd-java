package com.tycode.ecm.shared.domain.criteria;

import java.util.Optional;

public record Order(OrderBy orderBy, OrderType orderType) {

    public static Order fromValues(Optional<String> orderBy, Optional<String> orderType) {
        return orderBy.map(order -> new Order(new OrderBy(order), OrderType.valueOf(orderType.orElse("ASC"))))
                .orElseGet(Order::none);
    }

    public static Order none() {
        return new Order(new OrderBy(""), OrderType.NONE);
    }

    public static Order desc(String orderBy) {
        return new Order(new OrderBy(orderBy), OrderType.DESC);
    }

    public static Order asc(String orderBy) {
        return new Order(new OrderBy(orderBy), OrderType.ASC);
    }

    public boolean hasOrder() {
        return !orderType.isNone();
    }

    public String serialize() {
        return String.format("%s.%s", orderBy.value(), orderType.value());
    }
}
