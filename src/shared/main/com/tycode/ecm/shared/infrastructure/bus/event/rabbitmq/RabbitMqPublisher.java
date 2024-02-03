package com.tycode.ecm.shared.infrastructure.bus.event.rabbitmq;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.event.Event;
import com.tycode.ecm.shared.infrastructure.bus.EventJsonSerializer;
import lombok.AllArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
@AllArgsConstructor
public final class RabbitMqPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(Event event, String exchangeName) throws AmqpException {
        String serializedDomainEvent = EventJsonSerializer.serialize(event);

        Message message = new Message(
            serializedDomainEvent.getBytes(),
            MessagePropertiesBuilder.newInstance()
                                    .setContentEncoding("utf-8")
                                    .setContentType("application/json")
                                    .build()
        );

        rabbitTemplate.send(exchangeName, event.eventName(), message);
    }

    public void publish(Message event, String exchangeName, String routingKey) throws AmqpException {
        rabbitTemplate.send(exchangeName, routingKey, event);
    }
}
