package com.transunion.automation.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

/**
 * Cucumber hooks for initial stage and actor management setup.
 */
public class CommonHooks {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
