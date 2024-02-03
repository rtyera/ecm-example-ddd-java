package com.tycode.ecm.seller.product.domain;

import com.tycode.ecm.seller.review.domain.ReviewAuthor;
import com.tycode.ecm.seller.review.domain.ReviewCreateOn;
import com.tycode.ecm.seller.review.domain.ReviewMessage;

import java.time.LocalDateTime;

public class ProductReview {
    private final ReviewAuthor author;
    private final ReviewMessage message;

    private ProductReview(ReviewAuthor author, ReviewMessage message){
        this.author = author;
        this.message = message;
    }

    public ProductReview(String author, String message) {
        this(new ReviewAuthor(author), new ReviewMessage(message));
    }

    public String getAuthor() {
        return author.value();
    }

    public String getMessage() {
        return message.value();
    }

}
