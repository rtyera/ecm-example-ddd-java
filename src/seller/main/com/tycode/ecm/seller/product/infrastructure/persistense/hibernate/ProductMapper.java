package com.tycode.ecm.seller.product.infrastructure.persistense.hibernate;

import com.tycode.ecm.seller.product.domain.Product;
import com.tycode.ecm.seller.product.domain.ProductReview;

import java.util.stream.Collectors;

public class ProductMapper {

    public static Product entityToDomain(ProductEntity productEntity){
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getImages(),
                productEntity.getStockQuantity(),
                productEntity.getReviews().stream().map(
                        r -> new ProductReview(r.author(), r.message())
                ).collect(Collectors.toSet())
        );
    }

    public static ProductEntity domainToEntity(Product product){
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .images(product.getImages())
                .stockQuantity(product.getStockQuantity())
                .reviews(
                        product.getReviews().stream().map(
                                r -> new ProductReviewJson(r.getAuthor(), r.getMessage())
                        ).collect(Collectors.toSet())
                ).build();
    }
}
