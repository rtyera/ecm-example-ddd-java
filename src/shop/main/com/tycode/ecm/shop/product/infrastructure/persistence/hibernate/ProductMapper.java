package com.tycode.ecm.shop.product.infrastructure.persistence.hibernate;

import com.tycode.ecm.shop.product.domain.Product;

public class ProductMapper {

    public static Product entityToDomain(ProductEntity productEntity){
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getImages(),
                productEntity.getStockQuantity()
        );
    }

    public static ProductEntity domainToEntity(Product product){
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .images(product.getImages())
                .stockQuantity(product.getStockQuantity())
                .build();
    }
}
