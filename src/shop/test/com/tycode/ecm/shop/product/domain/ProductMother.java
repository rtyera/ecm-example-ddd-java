package com.tycode.ecm.shop.product.domain;

import com.tycode.ecm.shared.domain.UuidMother;
import com.tycode.ecm.shop.product.application.create.CommandCreateProduct;

import java.util.List;

public class ProductMother {

    public static Product create(String id, String name, float price, List<String> images, int stockQuantity) {
        return new Product(id, name, price, images, stockQuantity);
    }

    public static Product fromId(String id) {
        return create(
                id,
                ProductNameMother.random(),
                ProductPriceMother.random(),
                ProductImagesMother.empty(),
                ProductStockQuantityMother.random()
        );
    }

    public static Product fromRequest(CommandCreateProduct request) {
        return create(
                request.id(),
                request.name(),
                request.price(),
                request.images(),
                request.stockQuantity()
        );
    }

    public static Product random() {
        return create(
                UuidMother.random(),
                ProductNameMother.random(),
                ProductPriceMother.random(),
                ProductImagesMother.empty(),
                ProductStockQuantityMother.random()
        );
    }

}
