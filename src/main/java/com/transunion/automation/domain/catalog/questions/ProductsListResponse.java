package com.transunion.automation.domain.catalog.questions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transunion.automation.domain.catalog.models.ProductsListResponseDto;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Question that parses the products list response body into ProductsListResponseDto.
 */
@Subject("the products list response")
public class ProductsListResponse implements Question<ProductsListResponseDto> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ProductsListResponse received() {
        return new ProductsListResponse();
    }

    @Override
    public ProductsListResponseDto answeredBy(Actor actor) {
        String responseBody = SerenityRest.lastResponse().asString();
        try {
            return OBJECT_MAPPER.readValue(responseBody, ProductsListResponseDto.class);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse products list response: " + e.getMessage(), e);
        }
    }
}
