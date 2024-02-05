package com.tycode.ecm.ms_shop.controller.product;

import com.tycode.ecm.ms_shop.ApplicationTestCase;
import org.junit.jupiter.api.Test;


final class TestGetProductShould extends ApplicationTestCase {

	@Test
	void find_an_existing_product() throws Exception {
		String id = "99ad55f5-6eab-4d73-b383-c63268e251e8";
		String body = """
			{
				"id" : "99ad55f5-6eab-4d73-b383-c63268e251e8",
				"name" : "Redmi Note 13",
				"price" : 200.0,
				"images" : [
					"localhost/my_favorite_photo"
				],
				"stockQuantity" : 105
			}
		""";

		givenThereIsAProduct(body);

		assertResponse(String.format("/product/%s", id), 200, body);
	}

	@Test
	void find_by_criteria_product() throws Exception {
		String id = "99ad55f5-6eab-4d73-b383-c63268e251e8";
		String body = """
				{
					"id" : "99ad55f5-6eab-4d73-b383-c63268e251e8",
					"name" : "Redmi Note 13",
					"price" : 200.0,
					"images" : [
						"localhost/my_favorite_photo"
					],
					"stockQuantity" : 105
				}
		""";

		String responseBody = """
				[
					{
						"id" : "99ad55f5-6eab-4d73-b383-c63268e251e8",
						"name" : "Redmi Note 13",
						"price" : 200.0,
						"images" : [
							"localhost/my_favorite_photo"
						],
						"stockQuantity" : 105
					}
				]
		""";

		givenThereIsAProduct(body);

		assertResponse("/product/by-criteria?name=Redmi Note 13", 200, responseBody);
	}

	@Test
	void no_find_a_non_existing_product() throws Exception {
		String id = "4ecc0cb3-05b2-4238-b7e1-1fbb0d5d3661";
		String body = """
 			{
 				"httpStatus": 204,
 				"message": "The Product with id 4ecc0cb3-05b2-4238-b7e1-1fbb0d5d3661 does not exist",
 				"type": "error"
			}
		""";

		assertResponse(String.format("/product/%s", id), 204, body);
	}

	private void givenThereIsAProduct(String body) throws Exception {
		assertRequestWithBody("POST", "/product", body, 201);
	}
}
