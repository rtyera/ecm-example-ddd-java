package com.tycode.ecm.shared.infrastructure.bus.event.rabbitmq;

import com.tycode.ecm.shared.infrastructure.bus.EventSubscriberInformation;

public final class RabbitMqQueueNameFormatter {

    public static String format(EventSubscriberInformation information) {
        return information.formatRabbitMqQueueName();
    }

    public static String formatRetry(EventSubscriberInformation information) {
        return String.format("%s.retry", format(information));
    }

    public static String formatDeadLetter(EventSubscriberInformation information) {
        return String.format("%s.dead_letter", format(information));
    }
}
