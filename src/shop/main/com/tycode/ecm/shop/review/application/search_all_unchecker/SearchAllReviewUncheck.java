package com.tycode.ecm.shop.review.application.search_all_unchecker;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.criteria.*;
import com.tycode.ecm.shop.review.domain.Review;
import com.tycode.ecm.shop.review.domain.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchAllReviewUncheck {

    private final ReviewRepository reviewRepository;

    public List<Review> search(){
        var criteria = new Criteria(
                new Filters(
                        List.of(
                                new Filter(new FilterField("checker"), FilterOperator.EQUAL, new FilterValue("false"))
                        )
                ),
                Order.none()
        );

        return reviewRepository.matching(criteria);
    }
}
