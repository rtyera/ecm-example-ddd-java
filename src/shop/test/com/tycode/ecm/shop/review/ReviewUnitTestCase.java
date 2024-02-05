package com.tycode.ecm.shop.review;

import com.tycode.ecm.shared.infrastructure.UnitTestCase;
import com.tycode.ecm.shop.review.domain.Review;
import com.tycode.ecm.shop.review.domain.ReviewId;
import com.tycode.ecm.shop.review.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class ReviewUnitTestCase extends UnitTestCase {
    protected ReviewRepository reviewRepository;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        reviewRepository = mock(ReviewRepository.class);
    }

    public void shouldFindReview(String id, Review review){
        Mockito.when(reviewRepository.findById(new ReviewId(id))).thenReturn(Optional.of(review));
    }

    public void shouldNotFindReview(String id){
        Mockito.when(reviewRepository.findById(new ReviewId(id))).thenReturn(Optional.empty());
    }

    public void shouldHaveSaved(Review review) {
        verify(reviewRepository, atLeastOnce()).save(review);
    }

}
