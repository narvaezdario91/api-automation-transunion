package com.transunion.automation.questions;

import com.transunion.automation.models.response.UserResponseDto;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Question that deserializes and returns the user response body into UserResponseDto.
 */
@Subject("the user response body")
public class UserResponseBody implements Question<UserResponseDto> {

    public static UserResponseBody received() {
        return new UserResponseBody();
    }

    @Override
    public UserResponseDto answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(UserResponseDto.class);
    }
}
