package com.transunion.automation.domain.account.questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transunion.automation.domain.account.models.UserDetailResponseDto;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Question that parses the user detail response body into UserDetailResponseDto.
 */
@Subject("the user detail response")
public class UserDetailResponse implements Question<UserDetailResponseDto> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static UserDetailResponse received() {
        return new UserDetailResponse();
    }

    @Override
    public UserDetailResponseDto answeredBy(Actor actor) {
        String responseBody = SerenityRest.lastResponse().asString();
        try {
            return OBJECT_MAPPER.readValue(responseBody, UserDetailResponseDto.class);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse user detail response: " + e.getMessage(), e);
        }
    }
}
