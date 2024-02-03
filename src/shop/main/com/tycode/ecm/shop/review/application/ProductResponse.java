package com.tycode.ecm.shop.review.application;

import com.tycode.ecm.shared.domain.bus.query.Response;
import com.tycode.ecm.shop.review.domain.Review;

public record ProductResponse(Review review) implements Response {}
