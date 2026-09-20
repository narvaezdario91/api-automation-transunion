package com.transunion.automation.utils.data;

import com.transunion.automation.models.request.UserRequestDto;
import net.datafaker.Faker;

import java.util.Locale;

/**
 * Factory pattern implementation for generating dynamic, randomized, and boundary test payloads.
 */
public final class UserDataFactory {

    private static final Faker FAKER = new Faker(Locale.US);

    private UserDataFactory() {
        // Prevent instantiation of utility class
    }

    /**
     * Generates a fully populated and valid user payload.
     */
    public static UserRequestDto validUser() {
        return UserRequestDto.builder()
                .name(FAKER.name().fullName())
                .job(FAKER.job().title())
                .email(FAKER.internet().emailAddress())
                .build();
    }

    /**
     * Generates a valid user with a specified custom name.
     */
    public static UserRequestDto userWithCustomName(String name) {
        return validUser().toBuilder()
                .name(name)
                .build();
    }

    /**
     * Generates an edge-case user payload without a job field.
     */
    public static UserRequestDto userWithoutJob() {
        return validUser().toBuilder()
                .job(null)
                .build();
    }

    /**
     * Generates a payload with an intentionally malformed email.
     */
    public static UserRequestDto userWithInvalidEmail() {
        return validUser().toBuilder()
                .email("invalid_email_format@" + FAKER.internet().domainWord())
                .build();
    }
}
