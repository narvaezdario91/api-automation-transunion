package com.transunion.automation.stepdefinitions.common;

import com.transunion.automation.core.config.EnvironmentConfig;
import com.transunion.automation.core.questions.ApiResponseCode;
import com.transunion.automation.core.questions.ApiResponseMessage;
import com.transunion.automation.core.questions.LastResponseStatusCode;
import com.transunion.automation.core.questions.ResponseSchemaMatches;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.assertj.core.api.Assertions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

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
        int actualCode = SerenityRest.lastResponse().jsonPath().getInt("responseCode");
        Assertions.assertThat(actualCode)
                .as("El código de respuesta no debería ser " + unexpectedCode)
                .isNotEqualTo(unexpectedCode);
    }
}
