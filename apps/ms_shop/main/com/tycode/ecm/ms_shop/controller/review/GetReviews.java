package com.tycode.ecm.ms_shop.controller.review;

import com.tycode.ecm.shared.domain.bus.query.QueryBus;
import com.tycode.ecm.shop.product.application.ProductResponse;
import com.tycode.ecm.shop.product.application.search_by_id.QuerySearchByIdProduct;
import com.tycode.ecm.shop.product.domain.Product;
import com.tycode.ecm.shop.review.application.ReviewListResponse;
import com.tycode.ecm.shop.review.application.search_all.QuerySearchAllReview;
import com.tycode.ecm.shop.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/review")
@RequiredArgsConstructor
public class GetReviews {

    private final QueryBus queryBus;

    @GetMapping("/all")
    @ResponseBody
    public List<Review> searchAll(){
        ReviewListResponse response = this.queryBus.ask(new QuerySearchAllReview());

        return response.reviewList();
    }
}


