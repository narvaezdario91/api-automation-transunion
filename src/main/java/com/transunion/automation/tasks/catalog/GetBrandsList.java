package com.transunion.automation.tasks.catalog;

import com.transunion.automation.utils.constants.Endpoints;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay Task to retrieve the list of all brands from the catalog.
 */
public class GetBrandsList implements Task {

    public static GetBrandsList fromCatalog() {
        return instrumented(GetBrandsList.class);
    }

    @Override
    @Step("{0} queries the brands list catalog")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(Endpoints.BRANDS_LIST)
                        .with(request -> request.relaxedHTTPSValidation())
        );
    }
}
