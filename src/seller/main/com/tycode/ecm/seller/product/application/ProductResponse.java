package com.tycode.ecm.seller.product.application;

import com.tycode.ecm.shared.domain.bus.query.Response;
import com.tycode.ecm.seller.product.domain.Product;

public record ProductResponse(Product product) implements Response {}
