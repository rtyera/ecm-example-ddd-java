package com.tycode.ecm.shop.review.domain;

import com.tycode.ecm.shared.domain.MotherCreator;

public class AuthorMother {

    public static String random() {
        return MotherCreator.random().name().name();
    }
}
