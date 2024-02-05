package com.tycode.ecm.shop.product.domain;

import com.tycode.ecm.shared.domain.MotherCreator;

public class ProductNameMother {
    public static String random(){
        return MotherCreator.random().commerce().productName();
    }
}
