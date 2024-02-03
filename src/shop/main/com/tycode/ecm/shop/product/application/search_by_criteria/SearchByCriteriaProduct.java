package com.tycode.ecm.shop.product.application.search_by_criteria;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.criteria.Criteria;
import com.tycode.ecm.shared.domain.criteria.Filters;
import com.tycode.ecm.shared.domain.criteria.Order;
import com.tycode.ecm.shop.product.domain.Product;
import com.tycode.ecm.shop.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchByCriteriaProduct {

    private final ProductRepository productRepository;

    public List<Product> match(Filters filters, Order order, Optional<Integer> limit, Optional<Integer> offset){
        Criteria criteria = new Criteria(filters, order, limit, offset);

        return productRepository.matching(criteria);
    }
}
