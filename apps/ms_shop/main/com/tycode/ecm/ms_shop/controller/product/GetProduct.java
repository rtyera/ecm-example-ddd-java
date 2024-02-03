package com.tycode.ecm.ms_shop.controller.product;

import com.tycode.ecm.shared.domain.bus.query.QueryBus;
import com.tycode.ecm.shop.product.application.ProductResponse;
import com.tycode.ecm.shop.product.application.search_by_id.QuerySearchByIdProduct;
import com.tycode.ecm.shop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/product")
@RequiredArgsConstructor
public class GetProduct {

    private final QueryBus queryBus;

    @GetMapping("/{id}")
    @ResponseBody
    public Product searchById(@PathVariable String id){
        ProductResponse response = this.queryBus.ask(new QuerySearchByIdProduct(id));

        return response.product();
    }
}


