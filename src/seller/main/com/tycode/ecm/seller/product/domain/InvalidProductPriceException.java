package com.tycode.ecm.seller.product.domain;

public class InvalidProductPriceException extends RuntimeException{

    public InvalidProductPriceException(String message) {
        super(message);
    }
}
