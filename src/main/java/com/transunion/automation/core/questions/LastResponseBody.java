package com.transunion.automation.core.questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Generic Screenplay Question to deserialize the last response body into any designated DTO class.
 *
 * @param <T> Target DTO type
 */
@Subject("the response body deserialized to target type")
public class LastResponseBody<T> implements Question<T> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final Class<T> targetClass;

    public LastResponseBody(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    /**
     * Factory method to deserialize response to the specified class.
     *
     * @param targetClass Target DTO class
     * @param <T>         Target type
     * @return Question instance
     */
    public static <T> LastResponseBody<T> of(Class<T> targetClass) {
        return new LastResponseBody<>(targetClass);
    }

    @Override
    public T answeredBy(Actor actor) {
        String responseBody = SerenityRest.lastResponse().asString();
        try {
            return OBJECT_MAPPER.readValue(responseBody, targetClass);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse response body as "
                    + targetClass.getSimpleName() + ": " + e.getMessage(), e);
        }
    }
}
