package com.tycode.ecm.ms_shop.controller.product;

import com.tycode.ecm.shared.domain.bus.command.CommandBus;
import com.tycode.ecm.shop.product.application.create.CommandCreateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/product")
@RequiredArgsConstructor
public class PostCreateProduct {

    private final CommandBus commandBus;

    @PostMapping()
    public void create(@RequestBody CommandCreateProduct commandCreateProduct){
        this.commandBus.dispatch(commandCreateProduct);
    }
}


