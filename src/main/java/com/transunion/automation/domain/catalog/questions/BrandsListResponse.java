package com.transunion.automation.domain.catalog.questions;

import com.transunion.automation.core.questions.LastResponseBody;
import com.transunion.automation.domain.catalog.models.BrandsListResponseDto;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Question that parses the brands list response body into BrandsListResponseDto.
 */
@Subject("the brands list response")
public class BrandsListResponse implements Question<BrandsListResponseDto> {

    public static BrandsListResponse received() {
        return new BrandsListResponse();
    }

    @Override
    public BrandsListResponseDto answeredBy(Actor actor) {
        return actor.asksFor(LastResponseBody.of(BrandsListResponseDto.class));
    }
}
