package com.tycode.ecm.shop.review.application.create;

import com.tycode.ecm.shared.domain.bus.command.Command;

import java.time.LocalDateTime;
import java.util.Date;

public record CommandCreateReview(String id, String productId, String author, String message, LocalDateTime createOn) implements Command {}
