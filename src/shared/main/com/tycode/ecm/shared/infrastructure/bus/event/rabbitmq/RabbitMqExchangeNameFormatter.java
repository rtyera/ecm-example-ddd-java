package com.tycode.ecm.shared.infrastructure.bus.event.rabbitmq;

public final class RabbitMqExchangeNameFormatter {
    public static String retry(String exchangeName) {
        return String.format("%s-retry", exchangeName);
    }

    public static String deadLetter(String exchangeName) {
        return String.format("%s-dead_letter", exchangeName);
    }
}
