package com.v2modules.tabylga.api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GraphQLClient {

    private static final String URL_GRAPHQL = "https://tabylga.app/graphql";

    public static Response sendRequest(String jsonBody) {
        return RestAssured.given()
                .contentType("application/json")
                .body(jsonBody)
                .post(URL_GRAPHQL);
    }
}
