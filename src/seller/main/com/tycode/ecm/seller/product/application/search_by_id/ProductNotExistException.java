package com.tycode.ecm.seller.product.application.search_by_id;

public class ProductNotExistException extends RuntimeException{

    public ProductNotExistException(String message){
        super(message);
    }
}
