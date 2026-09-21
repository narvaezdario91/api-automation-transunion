package com.transunion.automation.core.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Collections;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Reusable Screenplay Task to send form-urlencoded POST requests with UTF-8 encoding.
 */
public class PostForm implements Task {

    private final String endpoint;
    private final Map<String, ?> formParams;

    public PostForm(String endpoint, Map<String, ?> formParams) {
        this.endpoint = endpoint;
        this.formParams = formParams != null ? formParams : Collections.emptyMap();
    }

    /**
     * Factory method to send a POST request with form parameters.
     *
     * @param endpoint   Target endpoint URL
     * @param formParams Map of form parameters
     * @return Instrumented Task
     */
    public static PostForm to(String endpoint, Map<String, ?> formParams) {
        return instrumented(PostForm.class, endpoint, formParams);
    }

    /**
     * Factory method to send a POST request with no form parameters.
     *
     * @param endpoint Target endpoint URL
     * @return Instrumented Task
     */
    public static PostForm to(String endpoint) {
        return instrumented(PostForm.class, endpoint, Collections.emptyMap());
    }

    @Override
    @Step("{0} sends form POST to #endpoint")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(endpoint)
                        .with(request -> {
                            request.contentType(ContentType.URLENC.withCharset("UTF-8"))
                                    .relaxedHTTPSValidation();
                            formParams.forEach((key, value) -> {
                                if (value != null) {
                                    request.formParam(key, value);
                                }
                            });
                            return request;
                        })
        );
    }
}
