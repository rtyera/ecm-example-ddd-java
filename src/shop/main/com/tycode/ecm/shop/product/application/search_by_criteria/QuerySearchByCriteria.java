package com.tycode.ecm.shop.product.application.search_by_criteria;

import com.tycode.ecm.shared.domain.bus.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public record QuerySearchByCriteria(
        List<HashMap<String, String>> filters,
        Optional<String> orderBy,
        Optional<String> orderType,
        Optional<Integer> limit,
        Optional<Integer> offset) implements Query {}
