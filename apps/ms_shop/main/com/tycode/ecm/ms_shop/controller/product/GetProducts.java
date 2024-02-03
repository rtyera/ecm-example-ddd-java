package com.tycode.ecm.ms_shop.controller.product;

import com.tycode.ecm.shared.domain.bus.query.QueryBus;
import com.tycode.ecm.shop.product.application.ProductListResponse;
import com.tycode.ecm.shop.product.application.search_all.QuerySearchAllProduct;
import com.tycode.ecm.shop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/product")
@RequiredArgsConstructor
public class GetProducts {

    private final QueryBus queryBus;

    @GetMapping("/all")
    @ResponseBody
    public List<Product> searchAll(){
        ProductListResponse response = this.queryBus.ask(new QuerySearchAllProduct());
        return response.productList();
    }
}


