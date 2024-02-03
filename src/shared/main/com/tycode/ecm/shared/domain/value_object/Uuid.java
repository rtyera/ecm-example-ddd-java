package com.tycode.ecm.shared.domain.value_object;

import java.util.Objects;
import java.util.UUID;

public abstract class Uuid {

    final protected String value;

    public Uuid(String value) {
        ensureValidUuid(value);
        this.value = value;
    }

    public static String random(){
        return UUID.randomUUID().toString();
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Uuid that)) {
            return false;
        }

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private void ensureValidUuid(String value) throws InvalidUuidException {
        try {
            UUID.fromString(value);
        }catch (IllegalArgumentException e){
            throw new InvalidUuidException(String.format("UUID %s invalid!", value));
        }
    }

}
