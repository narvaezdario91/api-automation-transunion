package com.transunion.automation.domain.catalog.tasks;

import com.transunion.automation.core.constants.Endpoints;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay Task to retrieve the list of all products from the catalog.
 */
public class GetProductsList implements Task {

    public static GetProductsList fromCatalog() {
        return instrumented(GetProductsList.class);
    }

    @Override
    @Step("{0} queries the products list catalog")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(Endpoints.PRODUCTS_LIST)
                        .with(request -> request.relaxedHTTPSValidation())
        );
    }
}
