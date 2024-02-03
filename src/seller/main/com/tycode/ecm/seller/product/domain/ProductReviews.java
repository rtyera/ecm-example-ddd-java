package com.tycode.ecm.seller.product.domain;

import java.util.Set;

public class ProductReviews {

    private final Set<ProductReview> reviews;

    public ProductReviews(Set<ProductReview> reviews){
        this.reviews = reviews;
    }

    public Set<ProductReview> value(){
        return this.reviews;
    }

    public static ProductReviews empty(){
        return new ProductReviews(Set.of());
    }
}
