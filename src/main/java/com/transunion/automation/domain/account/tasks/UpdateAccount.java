package com.transunion.automation.domain.account.tasks;

import com.transunion.automation.core.constants.Endpoints;
import com.transunion.automation.domain.account.models.AccountData;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay Task to update a user account via PUT /api/updateAccount (API 13).
 */
public class UpdateAccount implements Task {

    private final AccountData accountData;

    public UpdateAccount(AccountData accountData) {
        this.accountData = accountData;
    }

    /**
     * Updates an account using the provided AccountData instance.
     *
     * @param accountData The updated account details
     * @return Instrumented Task
     */
    public static UpdateAccount withData(AccountData accountData) {
        return instrumented(UpdateAccount.class, accountData);
    }

    @Override
    @Step("{0} actualiza los datos de la cuenta para #accountData.email")
    public <T extends Actor> void performAs(T actor) {
        Map<String, String> formParams = accountData.toFormParamMap();

        actor.attemptsTo(
                Put.to(Endpoints.UPDATE_ACCOUNT)
                        .with(request -> {
                            request.contentType(ContentType.URLENC.withCharset("UTF-8"))
                                    .relaxedHTTPSValidation();
                            formParams.forEach(request::formParam);
                            return request;
                        })
        );
    }
}
