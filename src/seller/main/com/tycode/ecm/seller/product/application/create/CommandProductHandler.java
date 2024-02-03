package com.tycode.ecm.seller.product.application.create;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.command.CommandHandler;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandProductHandler implements CommandHandler<CommandCreateProduct> {

    private final CreateProduct createProduct;

    @Override
    public void handle(CommandCreateProduct command) {
        this.createProduct.create(command.id(), command.name(), command.price(), command.images(), command.stockQuantity());
    }
}
