package com.tycode.ecm.seller.product.domain;

public class InvalidProductStockQuantityException extends RuntimeException{

    public InvalidProductStockQuantityException(String message) {
        super(message);
    }
}
