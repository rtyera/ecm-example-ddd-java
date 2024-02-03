package com.tycode.ecm.shop.product.application.search_all;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shop.product.domain.Product;
import com.tycode.ecm.shop.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchAllProduct {

    private final ProductRepository productRepository;

    public List<Product> search(){
        return productRepository.findAll();
    }
}
