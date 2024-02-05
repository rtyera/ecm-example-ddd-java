package com.tycode.ecm.ms_shop.controller.product;

import com.tycode.ecm.ms_shop.ApplicationTestCase;
import org.junit.jupiter.api.Test;


final class TestPostProductShould extends ApplicationTestCase {

	@Test
	void create_an_product() throws Exception {
		String body = """
			{
				"id" : "40273d6d-b4d6-462a-a31d-7deecf44e318",
				"name" : "Redmi Note 13",
				"price" : 200.0,
				"images" : [
					"localhost/my_favorite_photo"
				],
				"stockQuantity" : 105
			}
		""";

		assertRequestWithBody("POST", "/product", body, 201);
	}

}
