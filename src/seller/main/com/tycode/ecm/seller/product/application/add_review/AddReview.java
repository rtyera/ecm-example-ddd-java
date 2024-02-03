package com.tycode.ecm.seller.product.application.add_review;

import com.tycode.ecm.seller.product.application.search_by_id.SearchByIdProduct;
import com.tycode.ecm.seller.product.domain.ProductRepository;
import com.tycode.ecm.shared.domain.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddReview {

    private final ProductRepository productRepository;
    private final SearchByIdProduct searchByIdProduct;

    public void add(String productId, String author, String message){
        var product = this.searchByIdProduct.search(productId);

        product.addReview(author, message);

        this.productRepository.save(product);
    }
}
