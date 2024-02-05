package com.tycode.ecm.shop.review.application.create;

import com.tycode.ecm.shared.domain.UuidMother;
import com.tycode.ecm.shop.review.domain.AuthorMother;
import com.tycode.ecm.shop.review.domain.CreateOnMother;
import com.tycode.ecm.shop.review.domain.MessageMother;

import java.time.LocalDateTime;

public final class CommandCreateReviewMother {
    public static CommandCreateReview create(String id, String productId, String author, String message, LocalDateTime createOn) {
        return new CommandCreateReview(id, productId, author, message, createOn);
    }

    public static CommandCreateReview random() {
        return create(UuidMother.random(), UuidMother.random(), AuthorMother.random(), MessageMother.random(), CreateOnMother.now());
    }
}
