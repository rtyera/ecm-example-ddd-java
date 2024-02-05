package com.tycode.ecm.shop.product.domain;

import com.tycode.ecm.shared.domain.MotherCreator;

public class ProductPriceMother {
    public static float random(){
        return Float.parseFloat(MotherCreator.random().commerce().price());
    }
}
