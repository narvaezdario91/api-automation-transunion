package com.transunion.automation.tasks.catalog;

import com.transunion.automation.utils.constants.Endpoints;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay Task to search products with or without search criteria.
 */
public class SearchProduct implements Task {

    private final String searchTerm;
    private final boolean hasParameter;

    public SearchProduct(String searchTerm, boolean hasParameter) {
        this.searchTerm = searchTerm;
        this.hasParameter = hasParameter;
    }

    public static SearchProduct withTerm(String searchTerm) {
        return instrumented(SearchProduct.class, searchTerm, true);
    }

    public static SearchProduct withoutParameters() {
        return instrumented(SearchProduct.class, null, false);
    }

    @Override
    @Step("{0} executes product search with term: #searchTerm")
    public <T extends Actor> void performAs(T actor) {
        if (hasParameter) {
            actor.attemptsTo(
                    Post.to(Endpoints.SEARCH_PRODUCT)
                            .with(request -> request
                                    .contentType(io.restassured.http.ContentType.URLENC.withCharset("UTF-8"))
                                    .formParam("search_product", searchTerm)
                                    .relaxedHTTPSValidation())
            );
        } else {
            actor.attemptsTo(
                    Post.to(Endpoints.SEARCH_PRODUCT)
                            .with(request -> request.relaxedHTTPSValidation())
            );
        }
    }
}
