package com.tycode.ecm.shop.product.domain;

import java.util.List;

public class ProductImages {
    private final List<String> images;

    public ProductImages(List<String> images){
        this.images=images;
    }

    public List<String> value(){
        return this.images;
    }
}
