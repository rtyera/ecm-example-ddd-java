package com.tycode.ecm.shop.product.application;

import com.tycode.ecm.shared.domain.bus.query.Response;
import com.tycode.ecm.shop.product.domain.Product;

import java.util.List;

public record ProductListResponse(List<Product> productList) implements Response {}
