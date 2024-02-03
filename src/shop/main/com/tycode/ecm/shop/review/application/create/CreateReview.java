package com.tycode.ecm.shop.review.application.create;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shop.product.application.search_by_id.SearchByIdProduct;
import com.tycode.ecm.shop.review.domain.Review;
import com.tycode.ecm.shop.review.domain.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateReview {

    private final ReviewRepository reviewRepository;
    private final SearchByIdProduct searchByIdProduct;

    public void create(String id, String productId, String author, String message, LocalDateTime createOn){
        this.ensureProductExist(productId);

        reviewRepository.save(
                new Review(id, productId, author, message, createOn)
        );
    }

    private void ensureProductExist(String productId){
        this.searchByIdProduct.search(productId);
    }
}
