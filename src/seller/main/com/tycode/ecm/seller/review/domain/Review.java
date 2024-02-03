package com.tycode.ecm.seller.review.domain;

import com.tycode.ecm.seller.product.domain.ProductId;
import com.tycode.ecm.shared.domain.AggregateRoot;

import java.time.LocalDateTime;

public class Review extends AggregateRoot {

    private final ReviewId id;
    private final ProductId productId;

    private final ReviewAuthor author;

    private final ReviewMessage message;

    private final ReviewCreateOn createOn;



    private Review(ReviewId id, ProductId productId, ReviewAuthor author, ReviewMessage message, ReviewCreateOn createOn){
        this.id = id;
        this.productId = productId;
        this.author = author;
        this.message = message;
        this.createOn = createOn;
    }

    public Review(String id, String productId, String author, String message, LocalDateTime createOn) {
        this(new ReviewId(id), new ProductId(productId), new ReviewAuthor(author), new ReviewMessage(message), new ReviewCreateOn(createOn));
    }

    public static Review create(String id, String productId, String author, String message, LocalDateTime createOn){
        var review = new Review(new ReviewId(id), new ProductId(productId), new ReviewAuthor(author), new ReviewMessage(message), new ReviewCreateOn(createOn));

        review.record(new CreateReviewEvent(
                review.getId(),
                review.getProductId(),
                review.getAuthor(),
                review.getMessage(),
                review.getCreateOn().toString()
        ));

        return review;
    }

    public Review(String id, String productId, String author, String message, LocalDateTime createOn, boolean deliver, boolean checker) {
        this(new ReviewId(id), new ProductId(productId), new ReviewAuthor(author), new ReviewMessage(message), new ReviewCreateOn(createOn));
    }

    public String getId() {
        return id.value();
    }

    public String getProductId() {
        return productId.value();
    }

    public String getAuthor() {
        return author.value();
    }

    public String getMessage() {
        return message.value();
    }

    public LocalDateTime getCreateOn() {
        return createOn.value();
    }

}
