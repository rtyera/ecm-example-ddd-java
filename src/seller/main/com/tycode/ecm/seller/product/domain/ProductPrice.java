package com.tycode.ecm.seller.product.domain;

public class ProductPrice{
    private final float price;

    public ProductPrice(float price) {
        ensureValidPrice(price);

        this.price=price;
    }

    public float value(){
        return this.price;
    }

    private void ensureValidPrice(float price){
        if(price <= 0){
            throw new InvalidProductPriceException("Price of a product may greater than to zero");
        }
    }
}
