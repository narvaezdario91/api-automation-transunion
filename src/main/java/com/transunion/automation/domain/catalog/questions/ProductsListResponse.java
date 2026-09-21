package com.transunion.automation.domain.catalog.questions;

import com.transunion.automation.core.questions.LastResponseBody;
import com.transunion.automation.domain.catalog.models.ProductsListResponseDto;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/**
 * Question that parses the products list response body into ProductsListResponseDto.
 */
@Subject("the products list response")
public class ProductsListResponse implements Question<ProductsListResponseDto> {

    public static ProductsListResponse received() {
        return new ProductsListResponse();
    }

    @Override
    public ProductsListResponseDto answeredBy(Actor actor) {
        return actor.asksFor(LastResponseBody.of(ProductsListResponseDto.class));
    }
}
