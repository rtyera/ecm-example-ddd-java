package com.tycode.ecm.shop.product.domain;

public class InvalidProductPriceException extends RuntimeException{

    public InvalidProductPriceException(String message) {
        super(message);
    }
}
