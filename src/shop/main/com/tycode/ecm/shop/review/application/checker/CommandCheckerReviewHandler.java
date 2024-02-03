package com.tycode.ecm.shop.review.application.checker;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.command.CommandHandler;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandCheckerReviewHandler implements CommandHandler<CommandCheckerReview> {

    private final CheckerReview checkerReview;

    @Override
    public void handle(CommandCheckerReview command) {
        this.checkerReview.checker(command.id(), command.deliver());
    }
}
