package com.tycode.ecm.shop.review.domain;

import com.tycode.ecm.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    Optional<Review> findById(ReviewId reviewId);

    List<Review> findAll();

    void save(Review review);

    void remove(Review review);

    List<Review> matching(Criteria criteria);
}
