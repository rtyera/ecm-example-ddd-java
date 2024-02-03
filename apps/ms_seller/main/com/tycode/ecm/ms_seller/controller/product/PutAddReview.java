package com.tycode.ecm.ms_seller.controller.product;

import com.tycode.ecm.seller.product.application.add_review.CommandAddReview;
import com.tycode.ecm.shared.domain.bus.command.CommandBus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/product")
@RequiredArgsConstructor
public class PutAddReview {

    private final CommandBus commandBus;

    @PutMapping("/review/{productId}")
    @ResponseBody
    public void addReview(@PathVariable String productId, @RequestBody ReviewBody reviewBody){
        this.commandBus.dispatch(new CommandAddReview(productId, reviewBody.author(), reviewBody.message()));
    }

    public record ReviewBody(String author, String message){}
}


