package com.transunion.automation.core.config;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

/**
 * Utility to resolve runtime environment configurations.
 */
public final class EnvironmentConfig {

    private static final String DEFAULT_BASE_URL = "https://automationexercise.com";
    private static final String BASE_URL_PROPERTY = "restapi.baseurl";
    private static final String FALLBACK_PROPERTY = "environments.automationexercise.restapi.baseurl";

    private EnvironmentConfig() {
        // Utility class
    }

    /**
     * Resolves the base URL from serenity environment properties or falls back to default.
     *
     * @return Resolved base URL
     */
    public static String getBaseUrl() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        return EnvironmentSpecificConfiguration.from(environmentVariables)
                .getOptionalProperty(BASE_URL_PROPERTY)
                .orElseGet(() -> EnvironmentSpecificConfiguration.from(environmentVariables)
                        .getOptionalProperty(FALLBACK_PROPERTY)
                        .orElse(DEFAULT_BASE_URL));
    }
}
