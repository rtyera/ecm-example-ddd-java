package com.tycode.ecm.shop.review.application;

import com.tycode.ecm.shared.domain.bus.query.Response;
import com.tycode.ecm.shop.review.domain.Review;

import java.util.List;

public record ReviewListResponse(List<Review> reviewList) implements Response {}
