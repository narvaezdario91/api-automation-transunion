package com.transunion.automation.domain.account.tasks;

import com.transunion.automation.domain.account.models.AccountDataFactory;
import com.transunion.automation.domain.auth.tasks.VerifyLogin;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay Task ensuring a user account exists before dependent tests execute.
 * If the user does not exist (404), it automatically provisions the user via CreateAccount.
 */
public class EnsureUserExists implements Task {

    private final String email;
    private final String password;

    public EnsureUserExists(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Ensures default test user exists.
     *
     * @return Instrumented Task
     */
    public static EnsureUserExists withDefaults() {
        return instrumented(EnsureUserExists.class, AccountDataFactory.DEFAULT_EMAIL, AccountDataFactory.DEFAULT_PASSWORD);
    }

    /**
     * Ensures user with specified credentials exists.
     *
     * @param email    Account email
     * @param password Account password
     * @return Instrumented Task
     */
    public static EnsureUserExists withCredentials(String email, String password) {
        return instrumented(EnsureUserExists.class, email, password);
    }

    @Override
    @Step("{0} asegura que el usuario #email exista en el sistema")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(VerifyLogin.withCredentials(email, password));

        int responseCode = 0;
        try {
            responseCode = SerenityRest.lastResponse().jsonPath().getInt("responseCode");
        } catch (Exception e) {
            // Ignore parse exception and proceed to recreate
        }

        if (responseCode != 200) {
            actor.attemptsTo(CreateAccount.withEmailAndPassword(email, password));
        }
    }
}
