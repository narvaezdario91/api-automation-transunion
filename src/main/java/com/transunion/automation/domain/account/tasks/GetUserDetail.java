package com.transunion.automation.domain.account.tasks;

import com.transunion.automation.core.constants.Endpoints;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay Task to retrieve user details via GET /api/getUserDetailByEmail (API 14).
 */
public class GetUserDetail implements Task {

    private final String email;

    public GetUserDetail(String email) {
        this.email = email;
    }

    /**
     * Queries user details by email.
     *
     * @param email Target user email
     * @return Instrumented Task
     */
    public static GetUserDetail forEmail(String email) {
        return instrumented(GetUserDetail.class, email);
    }

    @Override
    @Step("{0} consulta los detalles del usuario con email: #email")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(Endpoints.GET_USER_DETAIL_BY_EMAIL)
                        .with(request -> {
                            request.relaxedHTTPSValidation();
                            if (email != null) {
                                request.queryParam("email", email);
                            }
                            return request;
                        })
        );
    }
}
