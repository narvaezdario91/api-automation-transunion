package com.transunion.automation.stepdefinitions;

import com.transunion.automation.models.account.AccountDataFactory;
import com.transunion.automation.tasks.account.EnsureUserExists;
import com.transunion.automation.utils.config.EnvironmentConfig;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

/**
 * Cucumber hooks for initial stage, actor management, and test fixture setup.
 */
public class CommonHooks {

    @Before(order = 0)
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Before(value = "@requires_user or @api7", order = 1)
    public void ensureDefaultUserExists() {
        Actor actor = OnStage.theActorCalled("Automation Tester");
        actor.can(CallAnApi.at(EnvironmentConfig.getBaseUrl()));
        actor.attemptsTo(EnsureUserExists.withCredentials(
                AccountDataFactory.DEFAULT_EMAIL,
                AccountDataFactory.DEFAULT_PASSWORD
        ));
    }
}

