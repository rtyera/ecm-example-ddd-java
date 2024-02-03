package com.tycode.ecm.shop.review.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class ReviewCreateOn {

    private final LocalDateTime value;

    public ReviewCreateOn(LocalDateTime value){
        this.value = value;
    }

    public LocalDateTime value(){
        return this.value;
    }

    public ReviewCreateOn now(){
        return new ReviewCreateOn(LocalDateTime.now());
    }
}
