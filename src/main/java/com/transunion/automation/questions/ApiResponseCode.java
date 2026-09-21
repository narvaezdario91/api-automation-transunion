package com.transunion.automation.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Question that retrieves the responseCode integer field from the JSON body.
 */
@Subject("the internal responseCode in the body")
public class ApiResponseCode implements Question<Integer> {

    public static ApiResponseCode fromBody() {
        return new ApiResponseCode();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getInt("responseCode");
    }
}
