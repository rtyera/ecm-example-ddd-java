package com.tycode.ecm.seller.review.application.create;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.command.CommandHandler;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandReviewHandler implements CommandHandler<CommandCreateReview> {

    private final CreateReview createReview;

    @Override
    public void handle(CommandCreateReview command) {
        this.createReview.create(command.id(), command.productId(), command.author(), command.message());
    }
}
