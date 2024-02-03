package com.tycode.ecm.shared.domain.bus.event;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface EventSubscriber {
    Class<? extends Event>[] value();
}