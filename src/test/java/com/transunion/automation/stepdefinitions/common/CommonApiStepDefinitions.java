package com.transunion.automation.stepdefinitions.common;

import com.transunion.automation.core.config.EnvironmentConfig;
import com.transunion.automation.core.constants.Endpoints;
import com.transunion.automation.core.questions.ApiResponseCode;
import com.transunion.automation.core.questions.ApiResponseMessage;
import com.transunion.automation.core.questions.LastResponseStatusCode;
import com.transunion.automation.core.questions.ResponseSchemaMatches;
import com.transunion.automation.core.tasks.ExecuteUnsupportedMethod;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

/**
 * Universal Step Definitions for HTTP/REST interactions, actor initialization,
 * and standard response assertions in Spanish.
 */
public class CommonApiStepDefinitions {

    @Dado("que el actor está listo para consumir la API de Automation Exercise")
    public void queElActorEstaListoParaConsumirLaApiDeAutomationExercise() {
        String baseUrl = EnvironmentConfig.getBaseUrl();
        Actor actor = OnStage.theActorCalled("Catalog Quality Engineer");
        actor.can(CallAnApi.at(baseUrl));
    }

    @Cuando("el actor envía una solicitud no soportada {string} al endpoint de lista de productos")
    public void elActorEnviaUnaSolicitudNoSoportadaAlEndpointDeListaDeProductos(String httpMethod) {
        executeUnsupportedMethod(Endpoints.PRODUCTS_LIST, httpMethod);
    }

    @Cuando("el actor envía una solicitud no soportada {string} al endpoint de lista de marcas")
    public void elActorEnviaUnaSolicitudNoSoportadaAlEndpointDeListaDeMarcas(String httpMethod) {
        executeUnsupportedMethod(Endpoints.BRANDS_LIST, httpMethod);
    }

    @Cuando("el actor envía una solicitud no soportada {string} al endpoint de verificación de login")
    public void elActorEnviaUnaSolicitudNoSoportadaAlEndpointDeVerificacionDeLogin(String httpMethod) {
        executeUnsupportedMethod(Endpoints.VERIFY_LOGIN, httpMethod);
    }

    @Entonces("el código de estado de la respuesta debe ser {int}")
    public void elCodigoDeEstadoDeLaRespuestaDebeSer(Integer expectedStatusCode) {
        OnStage.theActorInTheSpotlight().should(seeThat(LastResponseStatusCode.is(), equalTo(expectedStatusCode)));
    }

    @Y("el cuerpo de la respuesta debe coincidir con el esquema JSON {string}")
    public void elCuerpoDeLaRespuestaDebeCoincidirConElEsquemaJson(String schemaPath) {
        OnStage.theActorInTheSpotlight().should(seeThat(ResponseSchemaMatches.fromPath(schemaPath), equalTo(true)));
    }

    @Entonces("el mensaje de respuesta debe ser {string}")
    public void elMensajeDeRespuestaDebeSer(String expectedMessage) {
        OnStage.theActorInTheSpotlight().should(seeThat(ApiResponseMessage.returned(), equalTo(expectedMessage)));
    }

    @Y("el código de respuesta en el cuerpo debe ser {int}")
    public void elCodigoDeRespuestaEnElCuerpoDebeSer(Integer expectedCode) {
        OnStage.theActorInTheSpotlight().should(seeThat(ApiResponseCode.fromBody(), equalTo(expectedCode)));
    }

    @Y("el código de respuesta en el cuerpo no debe ser {int}")
    public void elCodigoDeRespuestaEnElCuerpoNoDebeSer(Integer unexpectedCode) {
        OnStage.theActorInTheSpotlight().should(seeThat(ApiResponseCode.fromBody(), not(equalTo(unexpectedCode))));
    }

    private void executeUnsupportedMethod(String endpoint, String httpMethod) {
        OnStage.theActorInTheSpotlight().attemptsTo(ExecuteUnsupportedMethod.on(endpoint, httpMethod));
    }
}
