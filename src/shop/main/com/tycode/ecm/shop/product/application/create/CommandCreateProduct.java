package com.tycode.ecm.shop.product.application.create;

import com.tycode.ecm.shared.domain.bus.command.Command;

import java.util.List;

public record CommandCreateProduct(String id, String name, float price, List<String> images, int stockQuantity) implements Command {}
