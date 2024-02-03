package com.tycode.ecm.shop.product.application.search_by_criteria;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.query.QueryHandler;
import com.tycode.ecm.shared.domain.criteria.Filters;
import com.tycode.ecm.shared.domain.criteria.Order;
import com.tycode.ecm.shop.product.application.ProductListResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuerySearchByCriteriaHandler implements QueryHandler<QuerySearchByCriteria, ProductListResponse> {

    private final SearchByCriteriaProduct search;

    @Override
    public ProductListResponse handle(QuerySearchByCriteria query) {
        Filters filters = Filters.fromValues(query.filters());
        Order order   = Order.fromValues(query.orderBy(), query.orderType());

        return new ProductListResponse(this.search.match(filters, order, query.limit(), query.offset()));
    }
}
