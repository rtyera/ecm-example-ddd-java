package com.tycode.ecm.seller.product.application.add_review;

import com.tycode.ecm.shared.domain.bus.command.Command;

import java.util.List;

public record CommandAddReview(String productId, String author, String message) implements Command {}
