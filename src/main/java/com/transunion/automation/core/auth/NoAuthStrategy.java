package com.transunion.automation.core.auth;

import io.restassured.specification.RequestSpecification;

/**
 * Null-object pattern authentication strategy when no credentials are required.
 */
public class NoAuthStrategy implements AuthenticationStrategy {

    private static final NoAuthStrategy INSTANCE = new NoAuthStrategy();

    public static NoAuthStrategy noAuth() {
        return INSTANCE;
    }

    @Override
    public RequestSpecification apply(RequestSpecification requestSpecification) {
        return requestSpecification;
    }
}
