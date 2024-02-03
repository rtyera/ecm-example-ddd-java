package com.tycode.ecm.seller.product.domain;

import com.tycode.ecm.shared.domain.value_object.Uuid;

public final class ProductId extends Uuid {
    public ProductId(String value) {
        super(value);
    }
}
