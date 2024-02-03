package com.tycode.ecm.shared.infrastructure.bus.event.rabbitmq;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.event.Event;
import com.tycode.ecm.shared.domain.bus.event.EventBus;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Service
@Primary
public class RabbitMqEventBus implements EventBus {

    @Autowired
    private RabbitMqPublisher publisher;
    @Value("${RABBITMQ_EXCHANGE_NAME}")
    private String            exchangeName;

    @Override
    public void publish(List<Event> events) {
        events.forEach(this::publish);
    }

    private void publish(Event event) {
        try {
            this.publisher.publish(event, exchangeName);
            System.out.println("publishing");
        } catch (AmqpException error) {
            System.out.println(error.getMessage());
        }
    }
}
