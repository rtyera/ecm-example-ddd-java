package com.tycode.ecm.shop.product.application.create;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.event.EventBus;
import com.tycode.ecm.shop.product.domain.Product;
import com.tycode.ecm.shop.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateProduct {

    private final ProductRepository productRepository;
    private final EventBus eventBus;

    public void create(String id, String name, float price, List<String> images, int stockQuantity){
        var product = Product.create(id, name, price, images, stockQuantity);
        productRepository.save(product);

        this.eventBus.publish(product.pullDomainEvents());
    }
}
