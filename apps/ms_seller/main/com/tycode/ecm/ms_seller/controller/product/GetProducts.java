package com.tycode.ecm.ms_seller.controller.product;

import com.tycode.ecm.shared.domain.bus.query.QueryBus;
import com.tycode.ecm.seller.product.application.ProductListResponse;
import com.tycode.ecm.seller.product.application.search_all.QuerySearchAllProduct;
import com.tycode.ecm.seller.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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


