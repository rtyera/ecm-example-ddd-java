package com.tycode.ecm.shared.infrastructure.http;

import com.tycode.ecm.shared.domain.criteria.OperatorNotFoundException;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ParserParamRequestQuery {

    public static List<HashMap<String, String>> parseFilters(MultiValueMap<String, String> params) {
        if(params.containsKey("filter")){
            return parse(params.get("filter"));
        }

        return List.of();
    }

    private static List<HashMap<String, String>> parse(List<String> list){
        List<HashMap<String, String>> filters = new LinkedList<>();
        list.forEach( s -> {
            filters.add(filter(s));
        });

        return filters;
    }

    private static HashMap<String, String> filter(String value) {
        String[] split = value.split(":");

        return new HashMap<>(){{
            put("field", split[0]);
            put("operator", operator(split[1]));
            put("value", split[2]);
        }};
    }

    private static String operator(String operator) {
        return switch(operator){
            case "equal": yield "=";
            case "not_equal": yield "!=";
            case "gt": yield ">";
            case "lt": yield "<";
            case "contains": yield "CONTAINS";
            case "not_contains": yield "NOT_CONTAINS";
            default: throw new OperatorNotFoundException(String.format("Operator %s is not permit!", operator));
        };
    }

}
