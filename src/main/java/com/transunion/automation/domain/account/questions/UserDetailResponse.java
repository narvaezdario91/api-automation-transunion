package com.transunion.automation.domain.account.questions;

import com.transunion.automation.core.questions.LastResponseBody;
import com.transunion.automation.domain.account.models.UserDetailResponseDto;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Question that parses the user detail response body into UserDetailResponseDto.
 */
@Subject("the user detail response")
public class UserDetailResponse implements Question<UserDetailResponseDto> {

    public static UserDetailResponse received() {
        return new UserDetailResponse();
    }

    @Override
    public UserDetailResponseDto answeredBy(Actor actor) {
        return actor.asksFor(LastResponseBody.of(UserDetailResponseDto.class));
    }
}
