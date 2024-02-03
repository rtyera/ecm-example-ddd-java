package com.tycode.ecm.seller.product.domain;

import com.tycode.ecm.shared.domain.AggregateRoot;

import java.util.List;
import java.util.Set;

public class Product extends AggregateRoot {

    private final ProductId id;
    private final ProductName name;
    private final ProductPrice price;
    private final ProductImages images;
    private final ProductStockQuantity stockQuantity;
    private final ProductReviews reviews;

    private Product(ProductId id, ProductName name, ProductPrice price, ProductImages images, ProductStockQuantity stockQuantity, ProductReviews reviews){
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.stockQuantity = stockQuantity;
        this.reviews = reviews;
    }

    public Product(String id, String name, float price, List<String> images, int stockQuantity) {
        this(new ProductId(id),
             new ProductName(name),
             new ProductPrice(price),
             new ProductImages(images),
             new ProductStockQuantity(stockQuantity),
             ProductReviews.empty()
        );
    }

    public Product(String id, String name, float price, List<String> images, int stockQuantity, Set<ProductReview> reviews) {
        this(new ProductId(id),
                new ProductName(name),
                new ProductPrice(price),
                new ProductImages(images),
                new ProductStockQuantity(stockQuantity),
                new ProductReviews(reviews)
        );
    }

    public String getId() {
        return id.value();
    }

    public String getName() {
        return name.value();
    }

    public float getPrice() {
        return price.value();
    }

    public List<String> getImages() {
        return images.value();
    }

    public int getStockQuantity() {
        return stockQuantity.value();
    }

    public Set<ProductReview> getReviews() {
        return reviews.value();
    }

    public void addReview(String author, String message){
        this.getReviews().add(new ProductReview(author, message));
    }
}
