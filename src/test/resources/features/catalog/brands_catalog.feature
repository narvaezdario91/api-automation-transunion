# language: en
@catalog @brands
Feature: Brands Catalog API Automation
  As an API consumer
  I want to query the brands list from the catalog
  So that I can validate available brand listings, schema compliance, and unsupported operation handling

  @smoke @regression @contract
  Scenario: Successfully retrieve all brands and validate JSON schema contract
    Given the actor is ready to consume the Automation Exercise API
    When the actor queries the complete brands list
    Then the response status code should be 200
    And the response body should match the JSON schema "schemas/catalog/brands_list_schema.json"
    And the catalog should contain brands with valid identifiers

  @regression @negative
  Scenario: Attempt PUT to brands list endpoint and receive method not supported response
    Given the actor is ready to consume the Automation Exercise API
    When the actor sends an unsupported "PUT" request to the brands list endpoint
    Then the response message should be "This request method is not supported."
    And the response code in the body should be 405
