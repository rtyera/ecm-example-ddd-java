package com.tycode.ecm.seller.review.application.create;

import com.tycode.ecm.shared.domain.bus.command.Command;

import java.time.LocalDateTime;

public record CommandCreateReview(String id, String productId, String author, String message) implements Command {}
