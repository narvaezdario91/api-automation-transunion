package com.transunion.automation.questions;

import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import java.io.InputStream;

/**
 * Question that validates the last HTTP response against a JSON Schema contract.
 */
@Subject("the JSON schema validation of the response")
public class ResponseSchemaMatches implements Question<Boolean> {

    private final String schemaPath;

    public ResponseSchemaMatches(String schemaPath) {
        this.schemaPath = schemaPath;
    }

    public static ResponseSchemaMatches fromPath(String schemaPath) {
        return new ResponseSchemaMatches(schemaPath);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        InputStream schemaStream = getClass().getClassLoader().getResourceAsStream(schemaPath);
        if (schemaStream == null) {
            throw new IllegalArgumentException("JSON Schema not found on classpath: " + schemaPath);
        }
        SerenityRest.lastResponse().then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schemaStream));
        return true;
    }
}
