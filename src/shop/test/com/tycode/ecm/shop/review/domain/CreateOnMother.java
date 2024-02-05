package com.tycode.ecm.shop.review.domain;

import com.tycode.ecm.shared.domain.LocalDateTimeMother;
import com.tycode.ecm.shared.domain.MotherCreator;

import java.time.LocalDateTime;

public class CreateOnMother {

    public static LocalDateTime now() {
        return LocalDateTimeMother.now();
    }
}
