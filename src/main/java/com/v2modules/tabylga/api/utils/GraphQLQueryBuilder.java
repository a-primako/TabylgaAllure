package com.v2modules.tabylga.api.utils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class GraphQLQueryBuilder {

    private static final Gson gson = new Gson();

    public static String buildQuery(String query, Map<String, Object> variables) {
        Map<String, Object> body = new HashMap<>();
        body.put("query", query);

        if (variables != null && !variables.isEmpty()) {
            body.put("variables", variables);
        }

        return gson.toJson(body);
    }
}
