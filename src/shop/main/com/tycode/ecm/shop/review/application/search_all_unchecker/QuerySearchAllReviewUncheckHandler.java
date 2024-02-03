package com.tycode.ecm.shop.review.application.search_all_unchecker;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.query.QueryHandler;
import com.tycode.ecm.shop.review.application.ReviewListResponse;
import com.tycode.ecm.shop.review.domain.Review;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuerySearchAllReviewUncheckHandler implements QueryHandler<QuerySearchAllReviewUncheck, ReviewListResponse> {

    private final SearchAllReviewUncheck searchAllReviewUncheck;

    @Override
    public ReviewListResponse handle(QuerySearchAllReviewUncheck query) {
        List<Review> reviews = this.searchAllReviewUncheck.search();

        return new ReviewListResponse(reviews);
    }

}
