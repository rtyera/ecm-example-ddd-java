package com.tycode.ecm.seller.product.application.create;

import com.tycode.ecm.seller.product.domain.Product;
import com.tycode.ecm.seller.product.domain.ProductRepository;
import com.tycode.ecm.shared.domain.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateProduct {

    private final ProductRepository productRepository;

    public void create(String id, String name, float price, List<String> images, int stockQuantity){
        productRepository.save(
                new Product(id, name, price, images, stockQuantity)
        );
    }
}
