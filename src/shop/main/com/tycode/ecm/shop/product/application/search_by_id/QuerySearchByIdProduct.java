package com.tycode.ecm.shop.product.application.search_by_id;

import com.tycode.ecm.shared.domain.bus.query.Query;

public record QuerySearchByIdProduct(String id) implements Query {}
