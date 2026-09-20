package com.transunion.automation.interactions.auth;

import io.restassured.specification.RequestSpecification;

/**
 * Strategy interface for configuring HTTP authentication mechanisms dynamically.
 * Adheres to the Open/Closed and Strategy design patterns.
 */
@FunctionalInterface
public interface AuthenticationStrategy {
    RequestSpecification apply(RequestSpecification requestSpecification);
}
