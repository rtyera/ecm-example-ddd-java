package com.tycode.ecm.shop.review.application.search_all;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shop.review.domain.Review;
import com.tycode.ecm.shop.review.domain.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchAllReview {

    private final ReviewRepository reviewRepository;

    public List<Review> search(){
        return reviewRepository.findAll();
    }
}
