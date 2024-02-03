package com.tycode.ecm.shop.review.application.checker;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.event.EventBus;
import com.tycode.ecm.shop.review.application.search_by_id.SearchByIdReview;
import com.tycode.ecm.shop.review.domain.ReviewRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckerReview {

    private final ReviewRepository reviewRepository;
    private final SearchByIdReview searchByIdReview;
    private final EventBus eventBus;

    public void checker(String reviewId, boolean deliver){
        var review = this.searchByIdReview.search(reviewId);

        review.checker();
        if(deliver){
            review.deliver();
        }

        reviewRepository.save(review);

        this.eventBus.publish(review.pullDomainEvents());
    }
}
