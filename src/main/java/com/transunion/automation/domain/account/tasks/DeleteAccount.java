package com.transunion.automation.domain.account.tasks;

import com.transunion.automation.core.constants.Endpoints;
import com.transunion.automation.core.tasks.DeleteWithForm;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> formParams = new HashMap<>();
        if (email != null) {
            formParams.put("email", email);
        }
        if (password != null) {
            formParams.put("password", password);
        }
        actor.attemptsTo(DeleteWithForm.from(Endpoints.DELETE_ACCOUNT, formParams));
    }
}
