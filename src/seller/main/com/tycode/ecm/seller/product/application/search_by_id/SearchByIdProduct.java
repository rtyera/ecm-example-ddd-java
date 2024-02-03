package com.tycode.ecm.seller.product.application.search_by_id;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.seller.product.domain.Product;
import com.tycode.ecm.seller.product.domain.ProductId;
import com.tycode.ecm.seller.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchByIdProduct {

    private final ProductRepository productRepository;

    public Product search(String id){
        return productRepository.findById(new ProductId(id)).orElseThrow(
                ()-> new ProductNotExistException(String.format("The Product with id %s doesn't exist!", id))
        );
    }
}
