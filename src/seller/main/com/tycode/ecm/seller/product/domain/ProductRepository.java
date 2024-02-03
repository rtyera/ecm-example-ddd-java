package com.tycode.ecm.seller.product.domain;

import com.tycode.ecm.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(ProductId productId);

    List<Product> findAll();

    void save(Product product);

    void remove(Product product);

    List<Product> matching(Criteria criteria);
}
