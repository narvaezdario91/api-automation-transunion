package com.transunion.automation.stepdefinitions.auth;

import com.transunion.automation.core.constants.Endpoints;
import com.transunion.automation.domain.auth.tasks.VerifyLogin;
import com.transunion.automation.domain.catalog.tasks.ExecuteUnsupportedMethod;
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

    @Cuando("el actor envía una solicitud no soportada {string} al endpoint de verificación de login")
    public void elActorEnviaUnaSolicitudNoSoportadaAlEndpointDeVerificacionDeLogin(String httpMethod) {
        OnStage.theActorInTheSpotlight().attemptsTo(ExecuteUnsupportedMethod.on(Endpoints.VERIFY_LOGIN, httpMethod));
    }
}
