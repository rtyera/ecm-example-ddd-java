package com.tycode.ecm.shop.review.application.checker;

import com.tycode.ecm.shop.review.ReviewUnitTestCase;
import com.tycode.ecm.shop.review.application.search_by_id.SearchByIdReview;
import com.tycode.ecm.shop.review.domain.Review;
import com.tycode.ecm.shop.review.domain.ReviewMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class TestCheckerReviewHandlerShould extends ReviewUnitTestCase {
    private CheckerReviewHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new CheckerReviewHandler(
                new CheckerReview(reviewRepository, new SearchByIdReview(reviewRepository), eventBus)
        );
    }

    @Test
    void checker_and_deliver_a_valid_review() {
        Review review = ReviewMother.random();
        CommandCheckerReview  commandCheckerReview = CommandCheckerReviewMother.fromIdAndDeliver(review.getId());

        shouldFindReview(commandCheckerReview.id(), review);
        handler.handle(commandCheckerReview);

        Review reviewCheckedAndDeliver = ReviewMother.checkedAndDeliver(review);

        shouldHaveSaved(reviewCheckedAndDeliver);
    }

    @Test
    void checker_a_valid_review() {
        Review review = ReviewMother.random();
        CommandCheckerReview  commandCheckerReview = CommandCheckerReviewMother.fromIdAndNotDeliver(review.getId());

        shouldFindReview(commandCheckerReview.id(), review);
        handler.handle(commandCheckerReview);

        Review reviewChecked = ReviewMother.checked(review);

        shouldHaveSaved(reviewChecked);
    }

}
