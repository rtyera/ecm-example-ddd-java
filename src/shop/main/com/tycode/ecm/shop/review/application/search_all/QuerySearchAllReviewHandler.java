package com.tycode.ecm.shop.review.application.search_all;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.query.QueryHandler;
import com.tycode.ecm.shop.review.application.ReviewListResponse;
import com.tycode.ecm.shop.review.domain.Review;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuerySearchAllReviewHandler implements QueryHandler<QuerySearchAllReview, ReviewListResponse> {

    private final SearchAllReview searchAllReview;

    @Override
    public ReviewListResponse handle(QuerySearchAllReview query) {
        List<Review> reviews = this.searchAllReview.search();

        return new ReviewListResponse(reviews);
    }

}
