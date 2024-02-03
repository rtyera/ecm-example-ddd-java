package com.tycode.ecm.seller.product.application.search_all;

import com.tycode.ecm.seller.product.application.ProductListResponse;
import com.tycode.ecm.seller.product.domain.Product;
import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.query.QueryHandler;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuerySearchAllProductHandler implements QueryHandler<QuerySearchAllProduct, ProductListResponse> {

    private final SearchAllProduct searchAllProduct;

    @Override
    public ProductListResponse handle(QuerySearchAllProduct query) {
        List<Product> products = this.searchAllProduct.search();

        return new ProductListResponse(products);
    }

}
