package com.transunion.automation.domain.account.tasks;

import com.transunion.automation.core.constants.Endpoints;
import com.transunion.automation.domain.account.models.AccountData;
import com.transunion.automation.domain.account.models.AccountDataFactory;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay Task to create a user account via POST /api/createAccount (API 11).
 */
public class CreateAccount implements Task {

    private final AccountData accountData;

    public CreateAccount(AccountData accountData) {
        this.accountData = accountData;
    }

    /**
     * Creates an account using the provided AccountData instance.
     *
     * @param accountData The account details
     * @return Instrumented Task
     */
    public static CreateAccount withData(AccountData accountData) {
        return instrumented(CreateAccount.class, accountData);
    }

    /**
     * Creates an account with default standard test user details.
     *
     * @return Instrumented Task
     */
    public static CreateAccount withDefaults() {
        return instrumented(CreateAccount.class, AccountDataFactory.defaultUser());
    }

    /**
     * Creates an account with specified email and password, auto-populating remaining profile fields.
     *
     * @param email    Account email
     * @param password Account password
     * @return Instrumented Task
     */
    public static CreateAccount withEmailAndPassword(String email, String password) {
        return instrumented(CreateAccount.class, AccountDataFactory.withEmailAndPassword(email, password));
    }

    @Override
    @Step("{0} crea una cuenta de usuario para #accountData.email")
    public <T extends Actor> void performAs(T actor) {
        Map<String, String> formParams = accountData.toFormParamMap();

        actor.attemptsTo(
                Post.to(Endpoints.CREATE_ACCOUNT)
                        .with(request -> {
                            request.contentType(ContentType.URLENC.withCharset("UTF-8"))
                                    .relaxedHTTPSValidation();
                            formParams.forEach(request::formParam);
                            return request;
                        })
        );
    }
}
