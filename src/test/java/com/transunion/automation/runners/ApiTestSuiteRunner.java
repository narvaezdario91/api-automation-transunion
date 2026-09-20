package com.transunion.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Main Serenity Cucumber Test Suite Runner.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.transunion.automation.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {"pretty"},
        tags = "@catalog or @auth or @account or @smoke or @regression"
)
public class ApiTestSuiteRunner {
}
