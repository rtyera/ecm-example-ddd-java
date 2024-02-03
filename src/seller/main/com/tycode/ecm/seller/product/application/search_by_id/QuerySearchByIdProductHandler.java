package com.tycode.ecm.seller.product.application.search_by_id;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.query.QueryHandler;
import com.tycode.ecm.seller.product.application.ProductResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuerySearchByIdProductHandler implements QueryHandler<QuerySearchByIdProduct, ProductResponse> {

    private final SearchByIdProduct searchByIdProduct;

    @Override
    public ProductResponse handle(QuerySearchByIdProduct query) {
        var product = this.searchByIdProduct.search(query.id());

        return new ProductResponse(product);
    }

}
