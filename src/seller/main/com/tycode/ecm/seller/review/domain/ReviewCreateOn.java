package com.tycode.ecm.seller.review.domain;

import java.time.LocalDateTime;

public class ReviewCreateOn {

    private final LocalDateTime createOn;

    public ReviewCreateOn(LocalDateTime createOn){
        this.createOn = createOn;
    }

    public LocalDateTime value(){
        return this.createOn;
    }

    public ReviewCreateOn now(){
        return new ReviewCreateOn(LocalDateTime.now());
    }
}
