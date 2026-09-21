package com.transunion.automation.domain.catalog.tasks;

import com.transunion.automation.core.constants.Endpoints;
import com.transunion.automation.core.tasks.PostForm;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * Searches products with a specified keyword.
     *
     * @param searchTerm Search keyword
     * @return Instrumented Task
     */
    public static SearchProduct withTerm(String searchTerm) {
        return instrumented(SearchProduct.class, searchTerm, true);
    }

    /**
     * Executes product search without parameters.
     *
     * @return Instrumented Task
     */
    public static SearchProduct withoutParameters() {
        return instrumented(SearchProduct.class, null, false);
    }

    @Override
    @Step("{0} executes product search with term: #searchTerm")
    public <T extends Actor> void performAs(T actor) {
        if (hasParameter && searchTerm != null) {
            Map<String, String> formParams = new HashMap<>();
            formParams.put("search_product", searchTerm);
            actor.attemptsTo(PostForm.to(Endpoints.SEARCH_PRODUCT, formParams));
        } else {
            actor.attemptsTo(PostForm.to(Endpoints.SEARCH_PRODUCT, Collections.emptyMap()));
        }
    }
}
