package com.tycode.ecm.shop.product.application;

import com.tycode.ecm.shared.domain.bus.query.Response;
import com.tycode.ecm.shop.product.domain.Product;

public record ProductResponse(Product product) implements Response {}
