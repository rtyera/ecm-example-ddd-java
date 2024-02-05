package com.tycode.ecm.ms_shop.controller.review;

import com.tycode.ecm.ms_shop.ApplicationTestCase;
import org.junit.jupiter.api.Test;

final class TestGetReviewShould extends ApplicationTestCase {

	@Test
	void find_all_existing_review() throws Exception {
		assertResponse("/review/all", 200, "[]");
	}

	@Test
	void find_all_uncheck_review() throws Exception {

	}

}
