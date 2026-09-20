package com.transunion.automation.tasks.user;

import com.transunion.automation.interactions.auth.AuthenticationStrategy;
import com.transunion.automation.interactions.auth.NoAuthStrategy;
import com.transunion.automation.utils.constants.Endpoints;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay Task to execute user query by identifier.
 */
public class QueryUserById implements Task {

    private final String userId;
    private final AuthenticationStrategy authStrategy;

    public QueryUserById(String userId, AuthenticationStrategy authStrategy) {
        this.userId = userId;
        this.authStrategy = authStrategy;
    }

    public static QueryUserById withId(String userId) {
        return instrumented(QueryUserById.class, userId, NoAuthStrategy.noAuth());
    }

    public static QueryUserById withIdAndAuth(String userId, AuthenticationStrategy authStrategy) {
        return instrumented(QueryUserById.class, userId, authStrategy);
    }

    @Override
    @Step("{0} executes GET to query user by id: #userId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(Endpoints.USER_BY_ID)
                        .with(request -> authStrategy.apply(request)
                                .contentType(ContentType.JSON)
                                .pathParam("id", userId)
                                .relaxedHTTPSValidation())
        );
    }
}
