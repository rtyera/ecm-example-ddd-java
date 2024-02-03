package com.tycode.ecm.shared.infrastructure.bus.event.rabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
@AllArgsConstructor
public class RabbitUpConsumer implements CommandLineRunner {
    private final RabbitMqEventsConsumer consumer;

    @Override
    public void run(String... args) throws Exception {
        consumer.consume();
    }
}
