package com.tycode.ecm.shop.product.domain;

import com.tycode.ecm.shared.domain.AggregateRoot;

import java.util.List;

public class Product extends AggregateRoot {
    private final ProductId id;
    private final ProductName name;
    private final ProductPrice price;
    private final ProductImages images;
    private final ProductStockQuantity stockQuantity;

    private Product(ProductId id, ProductName name, ProductPrice price, ProductImages images, ProductStockQuantity stockQuantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.stockQuantity = stockQuantity;
    }

    public Product(String id, String name, float price, List<String> images, int stockQuantity) {
        this(new ProductId(id), new ProductName(name), new ProductPrice(price), new ProductImages(images), new ProductStockQuantity(stockQuantity));
    }

    public static Product create(String id, String name, float price, List<String> images, int stockQuantity){
        var product = new Product(id, name, price, images, stockQuantity);

        product.record(
                new CreatedProductEvent(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getImages(),
                        product.getStockQuantity()
                )
        );

        return product;
    }

    public String getId(){
        return id.value();
    }

    public String getName(){
        return name.value();
    }

    public float getPrice(){
        return price.value();
    }

    public List<String> getImages(){
        return images.value();
    }

    public int getStockQuantity(){
        return stockQuantity.value();
    }
}
