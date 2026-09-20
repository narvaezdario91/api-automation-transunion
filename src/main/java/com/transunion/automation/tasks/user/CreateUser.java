package com.transunion.automation.tasks.user;

import com.transunion.automation.interactions.auth.AuthenticationStrategy;
import com.transunion.automation.interactions.auth.NoAuthStrategy;
import com.transunion.automation.models.request.UserRequestDto;
import com.transunion.automation.utils.constants.Endpoints;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay Task to execute user creation.
 */
public class CreateUser implements Task {

    private final UserRequestDto userRequest;
    private final AuthenticationStrategy authStrategy;

    public CreateUser(UserRequestDto userRequest, AuthenticationStrategy authStrategy) {
        this.userRequest = userRequest;
        this.authStrategy = authStrategy;
    }

    public static CreateUser withData(UserRequestDto userRequest) {
        return instrumented(CreateUser.class, userRequest, NoAuthStrategy.noAuth());
    }

    public static CreateUser withDataAndAuth(UserRequestDto userRequest, AuthenticationStrategy authStrategy) {
        return instrumented(CreateUser.class, userRequest, authStrategy);
    }

    @Override
    @Step("{0} executes POST to create a user")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(Endpoints.USERS)
                        .with(request -> authStrategy.apply(request)
                                .contentType(ContentType.JSON)
                                .body(userRequest)
                                .relaxedHTTPSValidation())
        );
    }
}
