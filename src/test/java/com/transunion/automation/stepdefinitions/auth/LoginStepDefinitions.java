package com.transunion.automation.stepdefinitions.auth;

import com.transunion.automation.domain.auth.tasks.VerifyLogin;
import io.cucumber.java.es.Cuando;
import net.serenitybdd.screenplay.actors.OnStage;

/**
 * Step Definitions for Automation Exercise user login verification operations.
 */
public class LoginStepDefinitions {

    @Cuando("el actor verifica el login con email {string} y contraseña {string}")
    public void elActorVerificaElLoginConEmailYContrasena(String email, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyLogin.withCredentials(email, password));
    }

    @Cuando("el actor verifica el login sin proporcionar el email con contraseña {string}")
    public void elActorVerificaElLoginSinProporcionarElEmailConContrasena(String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyLogin.withoutEmail(password));
    }

    @Cuando("el actor verifica el login sin proporcionar la contraseña con email {string}")
    public void elActorVerificaElLoginSinProporcionarLaContrasenaConEmail(String email) {
        OnStage.theActorInTheSpotlight().attemptsTo(VerifyLogin.withoutPassword(email));
    }
}
