package com.tycode.ecm.shop.review.application.search_by_id;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shop.review.domain.Review;
import com.tycode.ecm.shop.review.domain.ReviewId;
import com.tycode.ecm.shop.review.domain.ReviewRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchByIdReview {

    private final ReviewRepository reviewRepository;

    public Review search(String id){
        return reviewRepository.findById(new ReviewId(id)).orElseThrow(
                ()-> new ReviewNotExistException(String.format("The Review with id %s doesn't exist!", id))
        );
    }
}
