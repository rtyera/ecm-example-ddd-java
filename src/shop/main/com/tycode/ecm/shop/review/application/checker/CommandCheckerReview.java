package com.tycode.ecm.shop.review.application.checker;

import com.tycode.ecm.shared.domain.bus.command.Command;

public record CommandCheckerReview(String id, boolean deliver) implements Command {}
