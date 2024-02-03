package com.tycode.ecm.seller.review.application.create;

import com.tycode.ecm.seller.review.domain.Review;
import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.event.EventBus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateReview {

    private final EventBus eventBus;

    public void create(String id, String productId, String author, String message){
        var review = Review.create(id, productId, author, message, LocalDateTime.now());

        this.eventBus.publish(review.pullDomainEvents());
    }
}
