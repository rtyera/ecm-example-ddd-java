package com.tycode.ecm.shop.review.domain;

import com.tycode.ecm.shared.domain.LocalDateTimeMother;
import com.tycode.ecm.shared.domain.MotherCreator;
import com.tycode.ecm.shared.domain.UuidMother;
import com.tycode.ecm.shop.review.application.create.CommandCreateReview;

import java.time.LocalDateTime;

public class ReviewMother {

    public static Review create(String id, String productId, String author, String message, LocalDateTime createOn, boolean deliver, boolean checker) {
        return new Review(id, productId, author, message, createOn, deliver, checker);
    }

    public static Review fromRequest(CommandCreateReview request) {
        return create(
                request.id(),
                request.productId(),
                request.author(),
                request.message(),
                request.createOn(),
                false,
                false
        );
    }

    public static Review random() {
        return create(
                UuidMother.random(),
                UuidMother.random(),
                MotherCreator.random().name().name(),
                MotherCreator.random().lorem().paragraph(),
                LocalDateTimeMother.now(),
                false,
                false
        );
    }

    public static Review checked(Review review) {
        return create(
                review.getId(),
                review.getProductId(),
                review.getAuthor(),
                review.getMessage(),
                review.getCreateOn(),
                false,
                true
        );
    }

    public static Review checkedAndDeliver(Review review) {
        return create(
                review.getId(),
                review.getProductId(),
                review.getAuthor(),
                review.getMessage(),
                review.getCreateOn(),
                true,
                true
        );
    }
}
