package com.transunion.automation.core.auth;

import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * Authentication strategy that attaches an Authorization Bearer token header.
 */
@AllArgsConstructor
public class BearerTokenAuthStrategy implements AuthenticationStrategy {

    private final String token;

    public static BearerTokenAuthStrategy withToken(@NonNull String token) {
        return new BearerTokenAuthStrategy(token);
    }

    @Override
    public RequestSpecification apply(RequestSpecification requestSpecification) {
        return requestSpecification.header("Authorization", "Bearer " + token);
    }
}
