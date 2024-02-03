package com.tycode.ecm.ms_shop.controller.review;

import com.tycode.ecm.shared.domain.bus.command.CommandBus;
import com.tycode.ecm.shop.review.application.checker.CommandCheckerReview;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/review")
@RequiredArgsConstructor
public class PutCheckerReview {

    private final CommandBus commandBus;

    @PutMapping("/checked/{reviewId}")
    @ResponseBody
    public void addReview(@PathVariable String reviewId, @RequestBody CheckerBody checkerBody){
        this.commandBus.dispatch(
                new CommandCheckerReview(reviewId, checkerBody.deliver())
        );
    }

    public record CheckerBody(boolean deliver){}
}


