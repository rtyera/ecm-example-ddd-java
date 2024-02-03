package com.tycode.ecm.shop.product.domain;

public class InvalidProductStockQuantityException extends RuntimeException{

    public InvalidProductStockQuantityException(String message) {
        super(message);
    }
}
