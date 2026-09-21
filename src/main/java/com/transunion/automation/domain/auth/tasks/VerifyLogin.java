package com.transunion.automation.domain.auth.tasks;

import com.transunion.automation.core.constants.Endpoints;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay Task to verify user login credentials via POST /api/verifyLogin.
 * Supports valid credentials, missing email, missing password, and invalid credentials.
 */
public class VerifyLogin implements Task {

    private final String email;
    private final String password;
    private final boolean includeEmail;
    private final boolean includePassword;

    public VerifyLogin(String email, String password, boolean includeEmail, boolean includePassword) {
        this.email = email;
        this.password = password;
        this.includeEmail = includeEmail;
        this.includePassword = includePassword;
    }

    /** API 7 — POST with valid registered credentials. */
    public static VerifyLogin withCredentials(String email, String password) {
        return instrumented(VerifyLogin.class, email, password, true, true);
    }

    /** API 8 — POST without the email parameter. */
    public static VerifyLogin withoutEmail(String password) {
        return instrumented(VerifyLogin.class, null, password, false, true);
    }

    /** POST without the password parameter. */
    public static VerifyLogin withoutPassword(String email) {
        return instrumented(VerifyLogin.class, email, null, true, false);
    }

    /** API 10 — POST with invalid / non-existent credentials. */
    public static VerifyLogin withInvalidCredentials(String email, String password) {
        return instrumented(VerifyLogin.class, email, password, true, true);
    }

    @Override
    @Step("{0} verifica login con email: #email")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(Endpoints.VERIFY_LOGIN)
                        .with(request -> {
                            request.contentType(ContentType.URLENC.withCharset("UTF-8"))
                                    .relaxedHTTPSValidation();
                            if (includeEmail && email != null) {
                                request.formParam("email", email);
                            }
                            if (includePassword && password != null) {
                                request.formParam("password", password);
                            }
                            return request;
                        })
        );
    }
}
