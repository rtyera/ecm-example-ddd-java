package com.tycode.ecm.ms_shop.controller.product;

import com.tycode.ecm.shared.domain.bus.command.CommandBus;
import com.tycode.ecm.shop.product.application.create.CommandCreateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/product")
@RequiredArgsConstructor
public class PostCreateProduct {

    private final CommandBus commandBus;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CommandCreateProduct commandCreateProduct){
        this.commandBus.dispatch(commandCreateProduct);
    }
}


