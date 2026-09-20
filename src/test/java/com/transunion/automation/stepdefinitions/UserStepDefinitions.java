package com.transunion.automation.stepdefinitions;

import com.transunion.automation.models.request.UserRequestDto;
import com.transunion.automation.models.response.UserResponseDto;
import com.transunion.automation.questions.LastResponseStatusCode;
import com.transunion.automation.questions.ResponseSchemaMatches;
import com.transunion.automation.questions.UserResponseBody;
import com.transunion.automation.tasks.facades.UserOnboardingFacade;
import com.transunion.automation.tasks.user.CreateUser;
import com.transunion.automation.tasks.user.QueryUserById;
import com.transunion.automation.utils.data.UserDataFactory;
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
 * Step Definitions linking Cucumber Gherkin steps with Screenplay Actors and Tasks.
 */
public class UserStepDefinitions {

    @Given("the actor is ready to consume the API")
    public void theActorIsReadyToConsumeTheApi() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getOptionalProperty("environments.reqres.restapi.baseurl")
                .orElse("https://reqres.in");

        Actor actor = OnStage.theActorCalled("TransUnion Quality Engineer");
        actor.can(CallAnApi.at(baseUrl));
    }

    @When("the actor creates a new user with random dynamic data")
    public void theActorCreatesANewUserWithRandomDynamicData() {
        UserRequestDto payload = UserDataFactory.validUser();
        OnStage.theActorInTheSpotlight().attemptsTo(CreateUser.withData(payload));
    }

    @When("the actor creates a new user with name {string} and job {string}")
    public void theActorCreatesANewUserWithNameAndJob(String name, String job) {
        OnStage.theActorInTheSpotlight().attemptsTo(UserOnboardingFacade.createCustomUser(name, job));
    }

    @When("the actor queries the user with identifier {string}")
    public void theActorQueriesTheUserWithIdentifier(String userId) {
        OnStage.theActorInTheSpotlight().attemptsTo(QueryUserById.withId(userId));
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer expectedStatusCode) {
        OnStage.theActorInTheSpotlight().should(seeThat(LastResponseStatusCode.is(), equalTo(expectedStatusCode)));
    }

    @Then("the user response should contain a valid id and createdAt timestamp")
    public void theUserResponseShouldContainAValidIdAndCreatedAtTimestamp() {
        UserResponseDto response = OnStage.theActorInTheSpotlight().asksFor(UserResponseBody.received());
        Assertions.assertThat(response.getId()).isNotBlank();
        Assertions.assertThat(response.getCreatedAt()).isNotBlank();
    }

    @Then("the response body should match the JSON schema {string}")
    public void theResponseBodyShouldMatchTheJsonSchema(String schemaPath) {
        OnStage.theActorInTheSpotlight().should(seeThat(ResponseSchemaMatches.fromPath(schemaPath), equalTo(true)));
    }
}
