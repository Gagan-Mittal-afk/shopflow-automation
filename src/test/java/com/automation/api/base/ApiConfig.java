package com.automation.api.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.containsString;

/** Shared request defaults for the JSONPlaceholder API tests. */
public final class ApiConfig {

    public static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    private ApiConfig() {
    }

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addHeader("Accept", "application/json")
                .log(LogDetail.URI)
                .build();
    }

    public static ResponseSpecification jsonResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectHeader("Content-Type", containsString("application/json"))
                .build();
    }
}
