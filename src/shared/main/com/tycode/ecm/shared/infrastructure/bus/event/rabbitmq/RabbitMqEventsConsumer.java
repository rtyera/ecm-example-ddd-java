package com.tycode.ecm.shared.infrastructure.bus.event.rabbitmq;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.Utils;
import com.tycode.ecm.shared.domain.bus.event.Event;
import com.tycode.ecm.shared.domain.bus.event.NotRegisteredSubscriberException;
import com.tycode.ecm.shared.infrastructure.bus.EventJsonDeserializer;
import com.tycode.ecm.shared.infrastructure.bus.EventSubscribersInformation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Service
public final class RabbitMqEventsConsumer {
    private final String CONSUMER_NAME = "events_consumer";
    private final int MAX_RETRIES = 2;
    private final EventJsonDeserializer deserializer;
    private final ApplicationContext context;
    private final RabbitMqPublisher publisher;
    private final HashMap<String, Object> eventSubscribers = new HashMap<>();
    RabbitListenerEndpointRegistry registry;
    private EventSubscribersInformation information;

    public RabbitMqEventsConsumer(
        RabbitListenerEndpointRegistry registry,
        EventSubscribersInformation information,
        EventJsonDeserializer deserializer,
        ApplicationContext context,
        RabbitMqPublisher publisher
    ) {
        this.registry     = registry;
        this.information  = information;
        this.deserializer = deserializer;
        this.context      = context;
        this.publisher    = publisher;
    }

    public void consume() {
        AbstractMessageListenerContainer container = (AbstractMessageListenerContainer) registry.getListenerContainer(
            CONSUMER_NAME
        );

        container.addQueueNames(information.rabbitMqFormattedNames());
        container.start();
    }

    @RabbitListener(id = CONSUMER_NAME, autoStartup = "false")
    public void consumer(Message message) throws Exception {
        String serializedMessage = new String(message.getBody());
        Event event = deserializer.deserialize(serializedMessage);

        String queue = message.getMessageProperties().getConsumerQueue();

        Object subscriber = eventSubscribers.containsKey(queue)
            ? eventSubscribers.get(queue)
            : subscriberFor(queue);


        Method subscriberOnMethod = subscriber.getClass().getMethod("on", event.getClass());

        try {
            subscriberOnMethod.invoke(subscriber, event);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException error) {
            throw new Exception(String.format(
                "The subscriber <%s> should implement a method `on` listening the domain event <%s>",
                queue,
                event.eventName()
            ));
        } catch (Exception error) {
            handleConsumptionError(message, queue);
        }
    }

    public void withSubscribersInformation(EventSubscribersInformation information) {
        this.information = information;
    }

    private void handleConsumptionError(Message message, String queue) {
        if (hasBeenRedeliveredTooMuch(message)) {
            sendToDeadLetter(message, queue);
        } else {
            sendToRetry(message, queue);
        }
    }

    private void sendToRetry(Message message, String queue) {
        sendMessageTo(RabbitMqExchangeNameFormatter.retry("domain_events"), message, queue);
    }

    private void sendToDeadLetter(Message message, String queue) {
        sendMessageTo(RabbitMqExchangeNameFormatter.deadLetter("domain_events"), message, queue);
    }

    private void sendMessageTo(String exchange, Message message, String queue) {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();

        headers.put("redelivery_count", (int) headers.getOrDefault("redelivery_count", 0) + 1);

        MessageBuilder.fromMessage(message).andProperties(
            MessagePropertiesBuilder.newInstance()
                                    .setContentEncoding("utf-8")
                                    .setContentType("application/json")
                                    .copyHeaders(headers)
                                    .build());

        publisher.publish(message, exchange, queue);
    }

    private boolean hasBeenRedeliveredTooMuch(Message message) {
        return (int) message.getMessageProperties().getHeaders().getOrDefault("redelivery_count", 0) >= MAX_RETRIES;
    }

    private Object subscriberFor(String queue) throws NotRegisteredSubscriberException{
        String[] queueParts     = queue.split("\\.");
        String   subscriberName = Utils.toCamelFirstLower(queueParts[queueParts.length - 1]);

        try {
            Object subscriber = context.getBean(subscriberName);
            eventSubscribers.put(queue, subscriber);

            return subscriber;
        } catch (Exception e) {
            throw new NotRegisteredSubscriberException(String.format("There are not registered subscribers for %s queue", queue));
        }
    }
}
