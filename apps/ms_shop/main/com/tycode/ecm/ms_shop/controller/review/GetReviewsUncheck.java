package com.tycode.ecm.ms_shop.controller.review;

import com.tycode.ecm.shared.domain.bus.query.QueryBus;
import com.tycode.ecm.shop.review.application.ReviewListResponse;
import com.tycode.ecm.shop.review.application.search_all_unchecker.QuerySearchAllReviewUncheck;
import com.tycode.ecm.shop.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/review")
@RequiredArgsConstructor
public class GetReviewsUncheck {

    private final QueryBus queryBus;

    @GetMapping("/uncheck")
    @ResponseBody
    public List<Review> searchAll(){
        ReviewListResponse response = this.queryBus.ask(new QuerySearchAllReviewUncheck());

        return response.reviewList();
    }
}


