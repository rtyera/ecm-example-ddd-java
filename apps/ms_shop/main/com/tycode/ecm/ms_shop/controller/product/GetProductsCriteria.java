package com.tycode.ecm.ms_shop.controller.product;

import com.tycode.ecm.shared.domain.bus.query.QueryBus;
import com.tycode.ecm.shared.infrastructure.http.ParserParamRequestQuery;
import com.tycode.ecm.shop.product.application.ProductListResponse;
import com.tycode.ecm.shop.product.application.search_by_criteria.QuerySearchByCriteria;
import com.tycode.ecm.shop.product.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public final class GetProductsCriteria {

	private final QueryBus queryBus;

	@GetMapping("/by-criteria")
	public List<Product> search(@RequestParam MultiValueMap<String, String> params) {
		var filters = new QuerySearchByCriteria(
				ParserParamRequestQuery.parseFilters(params),
				Optional.ofNullable(params.containsKey("order_by") ? params.get("order_by").get(0) : null),
				Optional.ofNullable(params.containsKey("order") ? params.get("order").get(0) : null),
				Optional.ofNullable(params.containsKey("limit") ? Integer.parseInt(params.get("limit").get(0)) : null),
				Optional.ofNullable(params.containsKey("offset") ? Integer.parseInt(params.get("offset").get(0)) : null)
		);

		ProductListResponse response = this.queryBus.ask(filters);

		return response.productList();
	}

}
