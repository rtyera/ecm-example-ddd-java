package com.tycode.ecm.seller.product.application.add_review;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.command.CommandHandler;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandAddReviewHandler implements CommandHandler<CommandAddReview> {

    private final AddReview addReview;

    @Override
    public void handle(CommandAddReview command) {
        this.addReview.add(command.productId(), command.author(), command.message());
    }
}
