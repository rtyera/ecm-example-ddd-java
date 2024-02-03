package com.tycode.ecm.shop.review.infrastructure.persistence.hibernate;

import com.tycode.ecm.shop.review.domain.Review;

public class ReviewMapper {

    public static Review entityToDomain(ReviewEntity reviewEntity){
        return new Review(
                reviewEntity.getId(),
                reviewEntity.getProductId(),
                reviewEntity.getAuthor(),
                reviewEntity.getMessage(),
                reviewEntity.getCreateOn(),
                reviewEntity.isDeliver(),
                reviewEntity.isChecker()
        );
    }

    public static ReviewEntity domainToEntity(Review review){
        return ReviewEntity.builder()
                .id(review.getId())
                .productId(review.getProductId())
                .author(review.getAuthor())
                .message(review.getMessage())
                .createOn(review.getCreateOn())
                .deliver(review.isDeliver())
                .checker(review.isChecker())
                .build();
    }
}
