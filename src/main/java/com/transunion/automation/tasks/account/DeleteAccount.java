package com.transunion.automation.tasks.account;

import com.transunion.automation.utils.constants.Endpoints;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay Task to delete a user account via DELETE /api/deleteAccount (API 12).
 */
public class DeleteAccount implements Task {

    private final String email;
    private final String password;

    public DeleteAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Deletes an account with the provided email and password credentials.
     *
     * @param email    Account email
     * @param password Account password
     * @return Instrumented Task
     */
    public static DeleteAccount withCredentials(String email, String password) {
        return instrumented(DeleteAccount.class, email, password);
    }

    @Override
    @Step("{0} elimina la cuenta de usuario para email: #email")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(Endpoints.DELETE_ACCOUNT)
                        .with(request -> {
                            request.contentType(ContentType.URLENC.withCharset("UTF-8"))
                                    .relaxedHTTPSValidation();
                            if (email != null) {
                                request.formParam("email", email);
                            }
                            if (password != null) {
                                request.formParam("password", password);
                            }
                            return request;
                        })
        );
    }
}
