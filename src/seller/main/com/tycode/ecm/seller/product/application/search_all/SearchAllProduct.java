package com.tycode.ecm.seller.product.application.search_all;

import com.tycode.ecm.seller.product.domain.Product;
import com.tycode.ecm.seller.product.domain.ProductRepository;
import com.tycode.ecm.shared.domain.Service;
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
