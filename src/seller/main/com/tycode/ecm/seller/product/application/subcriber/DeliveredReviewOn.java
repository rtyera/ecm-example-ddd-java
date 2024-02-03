package com.tycode.ecm.seller.product.application.subcriber;

import com.tycode.ecm.seller.product.application.add_review.CommandAddReview;
import com.tycode.ecm.seller.product.application.event.DeliveredReviewEvent;
import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.command.CommandBus;
import com.tycode.ecm.shared.domain.bus.event.EventSubscriber;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;


@Service
@EventSubscriber({DeliveredReviewEvent.class})
@AllArgsConstructor
public class DeliveredReviewOn {
    private final CommandBus commandBus;

    @EventListener
    public void on(DeliveredReviewEvent event){

        this.commandBus.dispatch(new CommandAddReview(
                event.aggregateId(),
                event.getAuthor(),
                event.getMessage()
        ));
    }
}
