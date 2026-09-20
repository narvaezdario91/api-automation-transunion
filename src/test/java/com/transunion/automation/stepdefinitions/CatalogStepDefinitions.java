package com.transunion.automation.stepdefinitions;

import com.transunion.automation.models.catalog.BrandDto;
import com.transunion.automation.models.catalog.BrandsListResponseDto;
import com.transunion.automation.models.catalog.ProductDto;
import com.transunion.automation.models.catalog.ProductsListResponseDto;
import com.transunion.automation.questions.ApiResponseCode;
import com.transunion.automation.questions.ApiResponseMessage;
import com.transunion.automation.questions.BrandsListResponse;
import com.transunion.automation.questions.ProductsListResponse;
import com.transunion.automation.tasks.catalog.ExecuteUnsupportedMethod;
import com.transunion.automation.tasks.catalog.GetBrandsList;
import com.transunion.automation.tasks.catalog.GetProductsList;
import com.transunion.automation.tasks.catalog.SearchProduct;
import com.transunion.automation.utils.constants.Endpoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
 * Step Definitions for Automation Exercise Products and Brands Catalog API testing.
 */
public class CatalogStepDefinitions {

    @Given("the actor is ready to consume the Automation Exercise API")
    public void theActorIsReadyToConsumeTheAutomationExerciseApi() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getOptionalProperty("environments.automationexercise.restapi.baseurl")
                .orElse("https://automationexercise.com");

        Actor actor = OnStage.theActorCalled("Catalog Quality Engineer");
        actor.can(CallAnApi.at(baseUrl));
    }

    @When("the actor queries the complete products list")
    public void theActorQueriesTheCompleteProductsList() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetProductsList.fromCatalog());
    }

    @When("the actor sends an unsupported {string} request to the products list endpoint")
    public void theActorSendsAnUnsupportedRequestToTheProductsListEndpoint(String httpMethod) {
        OnStage.theActorInTheSpotlight().attemptsTo(ExecuteUnsupportedMethod.on(Endpoints.PRODUCTS_LIST, httpMethod));
    }

    @When("the actor queries the complete brands list")
    public void theActorQueriesTheCompleteBrandsList() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetBrandsList.fromCatalog());
    }

    @When("the actor sends an unsupported {string} request to the brands list endpoint")
    public void theActorSendsAnUnsupportedRequestToTheBrandsListEndpoint(String httpMethod) {
        OnStage.theActorInTheSpotlight().attemptsTo(ExecuteUnsupportedMethod.on(Endpoints.BRANDS_LIST, httpMethod));
    }

    @When("the actor searches products with keyword {string}")
    public void theActorSearchesProductsWithKeyword(String keyword) {
        OnStage.theActorInTheSpotlight().attemptsTo(SearchProduct.withTerm(keyword));
    }

    @When("the actor searches products without providing the search parameter")
    public void theActorSearchesProductsWithoutProvidingTheSearchParameter() {
        OnStage.theActorInTheSpotlight().attemptsTo(SearchProduct.withoutParameters());
    }

    @Then("the catalog should contain products with valid details")
    public void theCatalogShouldContainProductsWithValidDetails() {
        ProductsListResponseDto response = OnStage.theActorInTheSpotlight().asksFor(ProductsListResponse.received());
        Assertions.assertThat(response.getProducts())
                .as("Products list should not be empty")
                .isNotEmpty();

        ProductDto firstProduct = response.getProducts().get(0);
        Assertions.assertThat(firstProduct.getId()).isNotNull();
        Assertions.assertThat(firstProduct.getName()).isNotBlank();
        Assertions.assertThat(firstProduct.getPrice()).isNotBlank();
    }

    @Then("the catalog should contain brands with valid identifiers")
    public void theCatalogShouldContainBrandsWithValidIdentifiers() {
        BrandsListResponseDto response = OnStage.theActorInTheSpotlight().asksFor(BrandsListResponse.received());
        Assertions.assertThat(response.getBrands())
                .as("Brands list should not be empty")
                .isNotEmpty();

        BrandDto firstBrand = response.getBrands().get(0);
        Assertions.assertThat(firstBrand.getId()).isNotNull();
        Assertions.assertThat(firstBrand.getBrand()).isNotBlank();
    }

    @Then("the response message should be {string}")
    public void theResponseMessageShouldBe(String expectedMessage) {
        OnStage.theActorInTheSpotlight().should(seeThat(ApiResponseMessage.returned(), equalTo(expectedMessage)));
    }

    @Then("the response code in the body should be {int}")
    public void theResponseCodeInTheBodyShouldBe(Integer expectedCode) {
        OnStage.theActorInTheSpotlight().should(seeThat(ApiResponseCode.fromBody(), equalTo(expectedCode)));
    }

    @Then("all returned products should match the search criteria for {string}")
    public void allReturnedProductsShouldMatchTheSearchCriteriaFor(String keyword) {
        ProductsListResponseDto response = OnStage.theActorInTheSpotlight().asksFor(ProductsListResponse.received());
        Assertions.assertThat(response.getProducts())
                .as("Search result should contain matching products")
                .isNotEmpty();

        boolean anyMatchesKeyword = response.getProducts().stream()
                .anyMatch(product -> product.getName().toLowerCase().contains(keyword.toLowerCase())
                        || (product.getCategory() != null && product.getCategory().getCategory() != null
                        && product.getCategory().getCategory().toLowerCase().contains(keyword.toLowerCase())));

        Assertions.assertThat(anyMatchesKeyword)
                .as("At least one returned product should match the keyword: " + keyword)
                .isTrue();
    }
}
