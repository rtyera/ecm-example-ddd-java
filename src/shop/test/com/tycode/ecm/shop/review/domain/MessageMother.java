package com.tycode.ecm.shop.review.domain;

import com.tycode.ecm.shared.domain.MotherCreator;

public class MessageMother {

    public static String random() {
        return MotherCreator.random().lorem().paragraph();
    }
}
