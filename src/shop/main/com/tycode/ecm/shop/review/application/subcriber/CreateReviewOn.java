package com.tycode.ecm.shop.review.application.subcriber;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.command.CommandBus;
import com.tycode.ecm.shared.domain.bus.event.EventSubscriber;
import com.tycode.ecm.shop.review.application.create.CommandCreateReview;
import com.tycode.ecm.shop.review.application.event.CreateReviewEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;

@Service
@EventSubscriber({CreateReviewEvent.class})
@AllArgsConstructor
public class CreateReviewOn{
    private final CommandBus commandBus;

    @EventListener
    public void on(CreateReviewEvent event){

        this.commandBus.dispatch(new CommandCreateReview(
                event.aggregateId(),
                event.getProductId(),
                event.getAuthor(),
                event.getMessage(),
                LocalDateTime.parse(event.getCreateOn())
        ));
    }
}
