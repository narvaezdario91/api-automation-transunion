package com.transunion.automation.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Question that retrieves the HTTP status code of the most recent REST interaction.
 */
@Subject("the HTTP response status code")
public class LastResponseStatusCode implements Question<Integer> {

    public static LastResponseStatusCode is() {
        return new LastResponseStatusCode();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return SerenityRest.lastResponse().statusCode();
    }
}
