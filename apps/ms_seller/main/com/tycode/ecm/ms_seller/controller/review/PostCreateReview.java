package com.tycode.ecm.ms_seller.controller.review;

import com.tycode.ecm.seller.review.application.create.CommandCreateReview;
import com.tycode.ecm.shared.domain.bus.command.CommandBus;
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


