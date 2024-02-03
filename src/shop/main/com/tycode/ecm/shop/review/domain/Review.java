package com.tycode.ecm.shop.review.domain;

import com.tycode.ecm.shared.domain.AggregateRoot;
import com.tycode.ecm.shop.product.domain.ProductId;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

public class Review extends AggregateRoot {

    private final ReviewId id;
    private final ProductId productId;

    private final ReviewAuthor author;

    private final ReviewMessage message;

    private final ReviewCreateOn createOn;

    @Getter
    private boolean deliver;

    @Getter
    private boolean checker;


    private Review(ReviewId id, ProductId productId, ReviewAuthor author, ReviewMessage message, ReviewCreateOn createOn, boolean deliver, boolean checker){
        this.id = id;
        this.productId = productId;
        this.author = author;
        this.message = message;
        this.createOn = createOn;
        this.deliver = deliver;
        this.checker = checker;
    }

    public Review(String id, String productId, String author, String message, LocalDateTime createOn) {
        this(new ReviewId(id), new ProductId(productId), new ReviewAuthor(author), new ReviewMessage(message), new ReviewCreateOn(createOn), false, false);
    }

    public Review(String id, String productId, String author, String message, LocalDateTime createOn, boolean deliver, boolean checker) {
        this(new ReviewId(id), new ProductId(productId), new ReviewAuthor(author), new ReviewMessage(message), new ReviewCreateOn(createOn), deliver, checker);
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

    public void checker(){
        this.checker = true;
    }

    public void deliver(){
        this.deliver = true;

        this.record(
                new DeliveredReviewEvent(
                        this.getProductId(),
                        this.getAuthor(),
                        this.getMessage()
                )
        );
    }

}
