package com.tycode.ecm.ms_shop.controller.review;

import com.tycode.ecm.shared.domain.bus.command.CommandBus;
import com.tycode.ecm.shop.product.application.create.CommandCreateProduct;
import com.tycode.ecm.shop.review.application.create.CommandCreateReview;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/review")
@RequiredArgsConstructor
public class PostCreateReview {

    private final CommandBus commandBus;

    @PostMapping()
    public void create(@RequestBody CommandCreateReview commandCreateReview){
        this.commandBus.dispatch(commandCreateReview);
    }
}


