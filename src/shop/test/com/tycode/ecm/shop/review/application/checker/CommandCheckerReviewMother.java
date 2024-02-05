package com.tycode.ecm.shop.review.application.checker;

import com.tycode.ecm.shared.domain.UuidMother;

public final class CommandCheckerReviewMother {
    public static CommandCheckerReview create(String id, boolean deliver) {
        return new CommandCheckerReview(id, deliver);
    }

    public static CommandCheckerReview random() {
        return create(UuidMother.random(), true);
    }

    public static CommandCheckerReview fromIdAndDeliver(String id) {
        return create(id, true);
    }

    public static CommandCheckerReview fromIdAndNotDeliver(String id) {
        return create(id, false);
    }
}
