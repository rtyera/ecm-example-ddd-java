package com.tycode.ecm.shared.infrastructure.bus.event.rabbitmq;

import com.tycode.ecm.shared.infrastructure.bus.EventSubscribersInformation;
import com.tycode.ecm.shared.infrastructure.bus.EventsInformation;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class RabbitMqEventBusConfiguration {

    @Value("${RABBITMQ_EXCHANGE_NAME}")
    private String exchangeName;
    @Value("${RABBITMQ_HOST}")
    private String host;
    @Value("${RABBITMQ_PORT}")
    private int port;
    @Value("${RABBITMQ_LOGIN}")
    private String login;
    @Value("${RABBITMQ_PASSWORD}")
    private String password;
    @Autowired
    private EventSubscribersInformation eventSubscribersInformation;
    @Autowired
    private EventsInformation eventsInformation;

    @Bean
    public ConnectionFactory connection() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(this.host);
        factory.setPort(this.port);
        factory.setUsername(this.login);
        factory.setPassword(this.password);

        return factory;
    }

    @Bean
    public Declarables declaration() {
        String retryExchangeName      = RabbitMqExchangeNameFormatter.retry(exchangeName);
        String deadLetterExchangeName = RabbitMqExchangeNameFormatter.deadLetter(exchangeName);

        TopicExchange    eventsExchange           = new TopicExchange(exchangeName, true, false);
        TopicExchange    retryEventsExchange      = new TopicExchange(retryExchangeName, true, false);
        TopicExchange    deadLetterEventsExchange = new TopicExchange(deadLetterExchangeName, true, false);
        List<Declarable> declarables               = new ArrayList<>();

        declarables.add(eventsExchange);
        declarables.add(retryEventsExchange);
        declarables.add(deadLetterEventsExchange);

        Collection<Declarable> queuesAndBindings = declareQueuesAndBindings(
            eventsExchange,
            retryEventsExchange,
            deadLetterEventsExchange
        ).stream().flatMap(Collection::stream).toList();

        declarables.addAll(queuesAndBindings);

        return new Declarables(declarables);
    }

    private Collection<Collection<Declarable>> declareQueuesAndBindings(
        TopicExchange eventsExchange,
        TopicExchange retryEventsExchange,
        TopicExchange deadLetterEventsExchange
    ) {
        return eventSubscribersInformation.all().stream().map(information -> {
            String queueName           = RabbitMqQueueNameFormatter.format(information);
            String retryQueueName      = RabbitMqQueueNameFormatter.formatRetry(information);
            String deadLetterQueueName = RabbitMqQueueNameFormatter.formatDeadLetter(information);

            Queue queue = QueueBuilder.durable(queueName).build();
            Queue retryQueue = QueueBuilder.durable(retryQueueName).withArguments(
                retryQueueArguments(eventsExchange, queueName)
            ).build();
            Queue deadLetterQueue = QueueBuilder.durable(deadLetterQueueName).build();

            Binding fromExchangeSameQueueBinding = BindingBuilder
                .bind(queue)
                .to(eventsExchange)
                .with(queueName);

            Binding fromRetryExchangeSameQueueBinding = BindingBuilder
                .bind(retryQueue)
                .to(retryEventsExchange)
                .with(queueName);

            Binding fromDeadLetterExchangeSameQueueBinding = BindingBuilder
                .bind(deadLetterQueue)
                .to(deadLetterEventsExchange)
                .with(queueName);

            List<Binding> fromExchangeDomainEventsBindings = information.subscribedEvents().stream().map(
                eventClass -> {
                    String eventName = eventsInformation.forClass(eventClass);
                    return BindingBuilder
                        .bind(queue)
                        .to(eventsExchange)
                        .with(eventName);
                }).toList();

            List<Declarable> queuesAndBindings = new ArrayList<>();
            queuesAndBindings.add(queue);
            queuesAndBindings.add(fromExchangeSameQueueBinding);
            queuesAndBindings.addAll(fromExchangeDomainEventsBindings);

            queuesAndBindings.add(retryQueue);
            queuesAndBindings.add(fromRetryExchangeSameQueueBinding);

            queuesAndBindings.add(deadLetterQueue);
            queuesAndBindings.add(fromDeadLetterExchangeSameQueueBinding);

            return queuesAndBindings;
        }).collect(Collectors.toList());
    }

    private HashMap<String, Object> retryQueueArguments(TopicExchange exchange, String routingKey) {
        return new HashMap<String, Object>() {{
            put("x-dead-letter-exchange", exchange.getName());
            put("x-dead-letter-routing-key", routingKey);
            put("x-message-ttl", 1000);
        }};
    }
}
