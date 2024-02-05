package com.tycode.ecm.shared.domain;

import java.time.LocalDateTime;

public final class LocalDateTimeMother {

    public static LocalDateTime create(String value) {
        return LocalDateTime.parse(value);
    }

    public static LocalDateTime random() {
        return create(MotherCreator.random().date().toString());
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}
