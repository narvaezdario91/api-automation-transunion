package com.transunion.automation.domain.catalog.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Reusable Screenplay Task to execute an unsupported HTTP method on any designated endpoint.
 */
public class ExecuteUnsupportedMethod implements Task {

    private final String endpoint;
    private final String method;

    public ExecuteUnsupportedMethod(String endpoint, String method) {
        this.endpoint = endpoint;
        this.method = method.toUpperCase();
    }

    public static ExecuteUnsupportedMethod on(String endpoint, String method) {
        return instrumented(ExecuteUnsupportedMethod.class, endpoint, method);
    }

    @Override
    @Step("{0} executes unsupported #method on endpoint #endpoint")
    public <T extends Actor> void performAs(T actor) {
        switch (method) {
            case "POST":
                actor.attemptsTo(
                        Post.to(endpoint)
                                .with(request -> request.relaxedHTTPSValidation())
                );
                break;
            case "PUT":
                actor.attemptsTo(
                        Put.to(endpoint)
                                .with(request -> request.relaxedHTTPSValidation())
                );
                break;
            case "DELETE":
                actor.attemptsTo(
                        Delete.from(endpoint)
                                .with(request -> request.relaxedHTTPSValidation())
                );
                break;
            case "GET":
                actor.attemptsTo(
                        Get.resource(endpoint)
                                .with(request -> request.relaxedHTTPSValidation())
                );
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }
}
