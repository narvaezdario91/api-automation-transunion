package com.transunion.automation.stepdefinitions;

import com.transunion.automation.tasks.auth.VerifyLogin;
import com.transunion.automation.tasks.catalog.ExecuteUnsupportedMethod;
import com.transunion.automation.utils.constants.Endpoints;
import io.cucumber.java.es.Cuando;

/**
 * Step Definitions para la verificación de login de usuario vía /api/verifyLogin.
 * El paso @Dado de inicialización de actor está definido en CatalogStepDefinitions
 * y es compartido automáticamente por Cucumber en toda la suite.
 * Las aserciones (mensaje de respuesta, código de cuerpo) también son compartidas.
 */
public class LoginStepDefinitions {

    @Cuando("el actor verifica el login con email {string} y contraseña {string}")
    public void elActorVerificaElLoginConEmailYContrasena(String email, String password) {
        net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight()
                .attemptsTo(VerifyLogin.withCredentials(email, password));
    }

    @Cuando("el actor verifica el login sin proporcionar el email con contraseña {string}")
    public void elActorVerificaElLoginSinProporcionarElEmailConContrasena(String password) {
        net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight()
                .attemptsTo(VerifyLogin.withoutEmail(password));
    }

    @Cuando("el actor envía una solicitud no soportada {string} al endpoint de verificación de login")
    public void elActorEnviaUnaSolicitudNoSoportadaAlEndpointDeVerificacionDeLogin(String httpMethod) {
        net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight()
                .attemptsTo(ExecuteUnsupportedMethod.on(Endpoints.VERIFY_LOGIN, httpMethod));
    }
}
