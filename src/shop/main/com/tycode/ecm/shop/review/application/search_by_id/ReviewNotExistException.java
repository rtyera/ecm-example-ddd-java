package com.tycode.ecm.shop.review.application.search_by_id;

public class ReviewNotExistException extends RuntimeException{

    public ReviewNotExistException(String message){
        super(message);
    }
}
