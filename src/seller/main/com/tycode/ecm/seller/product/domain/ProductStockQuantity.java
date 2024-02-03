package com.tycode.ecm.seller.product.domain;

public class ProductStockQuantity {

    private final int stockQuantity;

    public ProductStockQuantity(int stockQuantity){
        this.stockQuantity = stockQuantity;
    }

    public int value(){
        return this.stockQuantity;
    }

    private void ensureValidStockQuantity(int stockQuantity) throws InvalidProductStockQuantityException{
        if(stockQuantity < 0){
            throw new InvalidProductStockQuantityException("Stock quantity not may less than to zero");
        }
    }
}
