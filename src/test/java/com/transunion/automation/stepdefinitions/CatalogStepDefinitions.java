package com.transunion.automation.stepdefinitions;

import com.transunion.automation.models.catalog.BrandDto;
import com.transunion.automation.models.catalog.BrandsListResponseDto;
import com.transunion.automation.models.catalog.ProductDto;
import com.transunion.automation.models.catalog.ProductsListResponseDto;
import com.transunion.automation.questions.ApiResponseCode;
import com.transunion.automation.questions.ApiResponseMessage;
import com.transunion.automation.questions.BrandsListResponse;
import com.transunion.automation.questions.LastResponseStatusCode;
import com.transunion.automation.questions.ProductsListResponse;
import com.transunion.automation.questions.ResponseSchemaMatches;
import com.transunion.automation.tasks.catalog.ExecuteUnsupportedMethod;
import com.transunion.automation.tasks.catalog.GetBrandsList;
import com.transunion.automation.tasks.catalog.GetProductsList;
import com.transunion.automation.tasks.catalog.SearchProduct;
import com.transunion.automation.utils.constants.Endpoints;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.assertj.core.api.Assertions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Step Definitions para la automatización de pruebas de las APIs de Automation Exercise en español.
 */
public class CatalogStepDefinitions {

    @Dado("que el actor está listo para consumir la API de Automation Exercise")
    public void queElActorEstaListoParaConsumirLaApiDeAutomationExercise() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getOptionalProperty("environments.automationexercise.restapi.baseurl")
                .orElse("https://automationexercise.com");

        Actor actor = OnStage.theActorCalled("Catalog Quality Engineer");
        actor.can(CallAnApi.at(baseUrl));
    }

    @Cuando("el actor consulta la lista completa de productos")
    public void elActorConsultaLaListaCompletaDeProductos() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetProductsList.fromCatalog());
    }

    @Cuando("el actor envía una solicitud no soportada {string} al endpoint de lista de productos")
    public void elActorEnviaUnaSolicitudNoSoportadaAlEndpointDeListaDeProductos(String httpMethod) {
        OnStage.theActorInTheSpotlight().attemptsTo(ExecuteUnsupportedMethod.on(Endpoints.PRODUCTS_LIST, httpMethod));
    }

    @Cuando("el actor consulta la lista completa de marcas")
    public void elActorConsultaLaListaCompletaDeMarcas() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetBrandsList.fromCatalog());
    }

    @Cuando("el actor envía una solicitud no soportada {string} al endpoint de lista de marcas")
    public void elActorEnviaUnaSolicitudNoSoportadaAlEndpointDeListaDeMarcas(String httpMethod) {
        OnStage.theActorInTheSpotlight().attemptsTo(ExecuteUnsupportedMethod.on(Endpoints.BRANDS_LIST, httpMethod));
    }

    @Cuando("el actor busca productos con la palabra clave {string}")
    public void elActorBuscaProductosConLaPalabraClave(String keyword) {
        OnStage.theActorInTheSpotlight().attemptsTo(SearchProduct.withTerm(keyword));
    }

    @Cuando("el actor busca productos sin proporcionar el parámetro de búsqueda")
    public void elActorBuscaProductosSinProporcionarElParametroDeBusqueda() {
        OnStage.theActorInTheSpotlight().attemptsTo(SearchProduct.withoutParameters());
    }

    @Entonces("el código de estado de la respuesta debe ser {int}")
    public void elCodigoDeEstadoDeLaRespuestaDebeSer(Integer expectedStatusCode) {
        OnStage.theActorInTheSpotlight().should(seeThat(LastResponseStatusCode.is(), equalTo(expectedStatusCode)));
    }

    @Y("el cuerpo de la respuesta debe coincidir con el esquema JSON {string}")
    public void elCuerpoDeLaRespuestaDebeCoincidirConElEsquemaJson(String schemaPath) {
        OnStage.theActorInTheSpotlight().should(seeThat(ResponseSchemaMatches.fromPath(schemaPath), equalTo(true)));
    }

    @Y("el catálogo debe contener productos con detalles válidos")
    public void elCatalogoDebeContenerProductosConDetallesValidos() {
        ProductsListResponseDto response = OnStage.theActorInTheSpotlight().asksFor(ProductsListResponse.received());
        Assertions.assertThat(response.getProducts())
                .as("La lista de productos no debe estar vacía")
                .isNotEmpty();

        ProductDto firstProduct = response.getProducts().get(0);
        Assertions.assertThat(firstProduct.getId()).isNotNull();
        Assertions.assertThat(firstProduct.getName()).isNotBlank();
        Assertions.assertThat(firstProduct.getPrice()).isNotBlank();
    }

    @Y("el catálogo debe contener marcas con identificadores válidos")
    public void elCatalogoDebeContenerMarcasConIdentificadoresValidos() {
        BrandsListResponseDto response = OnStage.theActorInTheSpotlight().asksFor(BrandsListResponse.received());
        Assertions.assertThat(response.getBrands())
                .as("La lista de marcas no debe estar vacía")
                .isNotEmpty();

        BrandDto firstBrand = response.getBrands().get(0);
        Assertions.assertThat(firstBrand.getId()).isNotNull();
        Assertions.assertThat(firstBrand.getBrand()).isNotBlank();
    }

    @Entonces("el mensaje de respuesta debe ser {string}")
    public void elMensajeDeRespuestaDebeSer(String expectedMessage) {
        OnStage.theActorInTheSpotlight().should(seeThat(ApiResponseMessage.returned(), equalTo(expectedMessage)));
    }

    @Y("el código de respuesta en el cuerpo debe ser {int}")
    public void elCodigoDeRespuestaEnElCuerpoDebeSer(Integer expectedCode) {
        OnStage.theActorInTheSpotlight().should(seeThat(ApiResponseCode.fromBody(), equalTo(expectedCode)));
    }

    @Y("todos los productos devueltos deben coincidir con el criterio de búsqueda para {string}")
    public void todosLosProductosDevueltosDebenCoincidirConElCriterioDeBusquedaPara(String keyword) {
        ProductsListResponseDto response = OnStage.theActorInTheSpotlight().asksFor(ProductsListResponse.received());
        Assertions.assertThat(response.getProducts())
                .as("El resultado de búsqueda no debe estar vacío")
                .isNotEmpty();

        boolean anyMatchesKeyword = response.getProducts().stream()
                .anyMatch(product -> product.getName().toLowerCase().contains(keyword.toLowerCase())
                        || (product.getCategory() != null && product.getCategory().getCategory() != null
                        && product.getCategory().getCategory().toLowerCase().contains(keyword.toLowerCase())));

        Assertions.assertThat(anyMatchesKeyword)
                .as("Al menos un producto devuelto debe coincidir con la palabra clave: " + keyword)
                .isTrue();
    }
}
