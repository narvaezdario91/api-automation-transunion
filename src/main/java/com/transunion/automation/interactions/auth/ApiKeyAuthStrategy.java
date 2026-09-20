package com.transunion.automation.interactions.auth;

import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * Authentication strategy that attaches an API Key header.
 */
@AllArgsConstructor
public class ApiKeyAuthStrategy implements AuthenticationStrategy {

    private final String headerName;
    private final String apiKey;

    public static ApiKeyAuthStrategy withHeader(@NonNull String headerName, @NonNull String apiKey) {
        return new ApiKeyAuthStrategy(headerName, apiKey);
    }

    public static ApiKeyAuthStrategy withDefaultHeader(@NonNull String apiKey) {
        return new ApiKeyAuthStrategy("x-api-key", apiKey);
    }

    @Override
    public RequestSpecification apply(RequestSpecification requestSpecification) {
        return requestSpecification.header(headerName, apiKey);
    }
}
