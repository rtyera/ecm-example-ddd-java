package com.tycode.ecm.shared.domain.criteria;

import java.util.HashMap;

public record Filter(FilterField field, FilterOperator operator, FilterValue value) {

    public static Filter create(String field, String operator, String value) {
        return new Filter(
                new FilterField(field),
                FilterOperator.fromValue(operator.toUpperCase()),
                new FilterValue(value)
        );
    }

    public static Filter fromValues(HashMap<String, String> values) {
        return new Filter(
                new FilterField(values.get("field")),
                FilterOperator.fromValue(values.get("operator")),
                new FilterValue(values.get("value"))
        );
    }

    public String serialize() {
        return String.format("%s.%s.%s", field.value(), operator.value(), value.value());
    }
}
