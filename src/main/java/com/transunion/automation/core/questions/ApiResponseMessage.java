package com.transunion.automation.core.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Question that extracts the message field from the JSON response body.
 */
@Subject("the API response message")
public class ApiResponseMessage implements Question<String> {

    public static ApiResponseMessage returned() {
        return new ApiResponseMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getString("message");
    }
}
