package com.transunion.automation.domain.catalog.questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transunion.automation.domain.catalog.models.BrandsListResponseDto;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Question that parses the brands list response body into BrandsListResponseDto.
 */
@Subject("the brands list response")
public class BrandsListResponse implements Question<BrandsListResponseDto> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static BrandsListResponse received() {
        return new BrandsListResponse();
    }

    @Override
    public BrandsListResponseDto answeredBy(Actor actor) {
        String responseBody = SerenityRest.lastResponse().asString();
        try {
            return OBJECT_MAPPER.readValue(responseBody, BrandsListResponseDto.class);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse brands list response: " + e.getMessage(), e);
        }
    }
}
